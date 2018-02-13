package com.alex.smartHome.src.controller;

import com.alex.smartHome.src.communication.WeatherServerConnection;

/**
 * Created by cosma on 30.05.2017.
 */
public class WeatherAdaptor {
    private static String appid = "3de087078c6d996263e51e0b9247f091";

    public String getLocationTemperature(String location) {
        String url = generateLocDefaultUrl(location);
        return new WeatherServerConnection(url).getTemperature();
    }

    private String generateLocDefaultUrl(String location) {
        String url = "http://api.openweathermap.org/data/2.5/weather?";
        url = url.concat("q=" + location);
        url = url.concat("&APPID=" + appid);
        url = url.concat("&mode=xml&units=metric");
        return url;
    }
}
