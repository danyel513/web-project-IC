/*

Weather class:

- provides a connection with a weather API and packs the data provided into
a weather object
- exposes the data about the weather and updates the data everytime it is requested

 */

package org.example.chromaglambackend.cg_service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

// adding a "@Service" tag the Weather Service will be a singleton
@Service
public class WeatherService
{
    // constants
    private static final String GEO_API_URL = "https://ipinfo.io/json";
    private static final String WEATHER_API_URL =
            "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s"
                    + "&hourly=temperature_2m,apparent_temperature,precipitation_probability,"
                    + "cloudcover,windspeed_10m,windgusts_10m,weathercode,uv_index,is_day"
                    + "&forecast_days=1";

    // dynamic field - updated everytime a request is made
    private String city;
    private String country;
    private double temperature;
    private double apparentTemperature;
    private double rainProbability;
    private double cloudCover;
    private double windSpeed;
    private double windGusts;
    private int weatherCode;
    private String weatherDescription;
    private double uvIndex;
    private boolean isDay;


    public WeatherService()
    {}

    private void updateData()
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
            JSONObject hourly = weatherData.getJSONObject("hourly");

            // Step 3: Extract key weather details
            city = locationData.getString("city");
            country = locationData.getString("country");
            temperature = hourly.getJSONArray("temperature_2m").getDouble(0);
            apparentTemperature = hourly.getJSONArray("apparent_temperature").getDouble(0);
            rainProbability = hourly.getJSONArray("precipitation_probability").getDouble(0);
            cloudCover = hourly.getJSONArray("cloudcover").getDouble(0);
            windSpeed = hourly.getJSONArray("windspeed_10m").getDouble(0);
            windGusts = hourly.getJSONArray("windgusts_10m").getDouble(0);
            weatherCode = hourly.getJSONArray("weathercode").getInt(0);
            weatherDescription = decodeWeatherCode(weatherCode);
            uvIndex = hourly.getJSONArray("uv_index").getDouble(0);
            isDay = hourly.getJSONArray("is_day").getInt(0) == 1;

        }
        catch (Exception e)
        {
            System.err.println("An error has occurred while connecting to the API that provides weather information: " + e.getMessage());
            e.printStackTrace();
            city = "Unknown";
            country = "Unknown";
            temperature = 0;
            apparentTemperature = 0;
            rainProbability = 0;
            cloudCover = 0;
            windSpeed = 0;
            windGusts = 0;
            uvIndex = 0;
            isDay = false;
            weatherDescription = "Unavailable";
        }
    }

    // transform weather code into string
    private String decodeWeatherCode(int code)
    {
        if (code == 0) return "Clear";
        else if (code == 1 || code == 2 || code == 3) return "Partly cloudy";
        else if (code == 45 || code == 48) return "Fog";
        else if (code >= 51 && code <= 67) return "Light or freezing rain";
        else if (code >= 71 && code <= 77) return "Snow";
        else if (code >= 80 && code <= 82) return "Heavy rain";
        else if (code >= 95 && code <= 99) return "Thunderstorm";
        else return "Unknown";
    }

    // Function to fetch API response
    private static String getApiResponse(String apiUrl) throws Exception
    {
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

    // get data about weather
    public WeatherData getWeatherDetails() {
        updateData();
        return new WeatherData(
                city, country, temperature, apparentTemperature, rainProbability,
                cloudCover, windSpeed, windGusts, weatherCode, weatherDescription,
                uvIndex, isDay
        );
    }
}
