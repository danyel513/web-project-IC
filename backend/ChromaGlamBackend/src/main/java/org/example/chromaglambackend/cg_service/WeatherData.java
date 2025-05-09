package org.example.chromaglambackend.cg_service;

public class WeatherData
{
    public String city;
    public String country;
    public double temperature;
    public double apparentTemperature;
    public double rainProbability;
    public double cloudCover;
    public double windSpeed;
    public double windGusts;
    public int weatherCode;
    public String weatherDescription;
    public double uvIndex;
    public boolean isDay;

    public WeatherData(String city, String country, double temperature, double apparentTemperature,
                       double rainProbability, double cloudCover, double windSpeed, double windGusts,
                       int weatherCode, String weatherDescription, double uvIndex, boolean isDay) {
        this.city = city;
        this.country = country;
        this.temperature = temperature;
        this.apparentTemperature = apparentTemperature;
        this.rainProbability = rainProbability;
        this.cloudCover = cloudCover;
        this.windSpeed = windSpeed;
        this.windGusts = windGusts;
        this.weatherCode = weatherCode;
        this.weatherDescription = weatherDescription;
        this.uvIndex = uvIndex;
        this.isDay = isDay;
    }
}
