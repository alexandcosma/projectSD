package com.alex.smartHome.src.controllers;

import model.RegularUser;

/**
 * Created by cosma on 10.05.2017.
 */
public class UserViewController {
    private RegularUser user;

    public UserViewController(RegularUser usr){
        user = usr;
    }

    public void setTemperature(String place, float newTemp){
        user.setTemperatureTo(place, newTemp);
    }
}
