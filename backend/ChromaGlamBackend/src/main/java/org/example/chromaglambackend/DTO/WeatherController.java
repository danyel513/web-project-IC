/*

WeatherController class will expose the weather data to the frontend.

 */
package org.example.chromaglambackend.DTO;

import org.example.chromaglambackend.cg_service.WeatherData;
import org.example.chromaglambackend.cg_service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class WeatherController
{
    private final WeatherService weatherService;

    @Autowired
    public WeatherController()
    {
        weatherService = new WeatherService();
    }

    @GetMapping("/weather")
    public ResponseEntity<WeatherData> getWeather() {
        return ResponseEntity.ok(weatherService.getWeatherDetails());
    }
}
