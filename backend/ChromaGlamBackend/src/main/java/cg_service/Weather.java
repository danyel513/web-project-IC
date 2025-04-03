/*

Weather class:

- provides a connection with a weather API and packs the data provided into a weather object


 */

package cg_service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Weather
{
    private static final String GEO_API_URL = "https://ipinfo.io/json";
    private static final String WEATHER_API_URL = "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&hourly=temperature_2m,apparent_temperature,precipitation_probability&forecast_days=1";

    public static void main(String[] args)
    {
        try {
            // Step 1: Get current location
            JSONObject locationData = new JSONObject(getApiResponse(GEO_API_URL));
            String loc = locationData.getString("loc"); // "45.7489,21.2087"
            String[] coordinates = loc.split(",");
            double latitude = Double.parseDouble(coordinates[0]);
            double longitude = Double.parseDouble(coordinates[1]);

            // Step 2: Get weather data
            String weatherUrl = String.format(WEATHER_API_URL, latitude, longitude);
            JSONObject weatherData = new JSONObject(getApiResponse(weatherUrl));

            // Step 3: Extract and display key weather details
            JSONObject hourly = weatherData.getJSONObject("hourly");
            System.out.println("📍 Location: " + locationData.getString("city") + ", " + locationData.getString("country"));
            System.out.println("🌡️ Temperature (next hour): " + hourly.getJSONArray("temperature_2m").getDouble(0) + "°C");
            System.out.println("🤔 Feels Like: " + hourly.getJSONArray("apparent_temperature").getDouble(0) + "°C");
            System.out.println("🌧️ Rain Probability: " + hourly.getJSONArray("precipitation_probability").getDouble(0) + "%");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function to fetch API response
    private static String getApiResponse(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }
}
