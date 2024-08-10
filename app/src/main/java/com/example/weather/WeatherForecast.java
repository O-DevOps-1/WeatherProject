package com.example.weather;

import java.util.ArrayList;

import java.util.List;

public class WeatherForecast {
    private List<WeatherData> weatherDataList;

    public WeatherForecast() {
        weatherDataList = new ArrayList<>();
    }

    public void addWeatherData(WeatherData weatherData) {
        weatherDataList.add(weatherData);
    }

    public List<WeatherData> getWeatherDataList() {
        return weatherDataList;
    }}
