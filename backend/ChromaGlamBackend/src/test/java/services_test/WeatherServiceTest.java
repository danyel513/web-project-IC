package services_test;
import cg_service.WeatherData;
import cg_service.WeatherService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherServiceTest
{
    @Test
    public void testWeatherDetailsAreFetched() {
        WeatherService weatherService = new WeatherService();
        WeatherData data = weatherService.getWeatherDetails();

        System.out.println("=== Weather Data ===");
        System.out.println("City: " + data.city);
        System.out.println("Country: " + data.country);
        System.out.printf("Temperature: %.1f°C\n", data.temperature);
        System.out.printf("Feels Like: %.1f°C\n", data.apparentTemperature);
        System.out.printf("Rain Probability: %.0f%%\n", data.rainProbability);
        System.out.printf("Cloud Cover: %.0f%%\n", data.cloudCover);
        System.out.printf("Wind Speed: %.1f km/h\n", data.windSpeed);
        System.out.printf("Wind Gusts: %.1f km/h\n", data.windGusts);
        System.out.println("Weather Code: " + data.weatherCode);
        System.out.println("Weather Description: " + data.weatherDescription);
        System.out.printf("UV Index: %.1f\n", data.uvIndex);
        System.out.println("Day/Night: " + (data.isDay ? "Day" : "Night"));
        System.out.println("=====================");

        assertNotNull(data, "Weather data should not be null");
        assertNotNull(data.city, "City should not be null");
        assertNotNull(data.country, "Country should not be null");

        assertTrue(data.temperature >= -30 && data.temperature <= 50, "Temperature looks valid");
        assertTrue(data.apparentTemperature >= -40 && data.apparentTemperature <= 50, "Apparent temp valid");
        assertTrue(data.rainProbability >= 0 && data.rainProbability <= 100, "Rain % valid");
        assertTrue(data.cloudCover >= 0 && data.cloudCover <= 100, "Cloud cover % valid");
        assertTrue(data.windSpeed >= 0, "Wind speed should be non-negative");
        assertTrue(data.windGusts >= 0, "Wind gusts should be non-negative");
        assertTrue(data.uvIndex >= 0, "UV index should be non-negative");

        assertNotNull(data.weatherDescription, "Weather description should not be null");

        double diff = Math.abs(data.temperature - data.apparentTemperature);
        assertTrue(diff < 15, "Apparent and actual temp shouldn't differ by more than 15°C");
    }
}
