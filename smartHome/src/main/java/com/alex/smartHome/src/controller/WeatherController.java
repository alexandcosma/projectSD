package com.alex.smartHome.src.controller;

/**
 * Created by cosma on 30.05.2017.
 */
public class WeatherController {
    public String getTemperature(String location) {
        return new WeatherAdaptor().getLocationTemperature(location);
    }
}
