package com.alex.smartHome.src.communication;

import model.House;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cosma on 19.05.2017.
 */
public class ClientHouse implements Serializable {
    private static final long serialVersionUID = -9038666452899928473L;

    private List<String> places;
    private List<Float> actTemperatures;
    private List<Float> reqTemperatures;
    private List<Boolean> status;

    public ClientHouse(House house){
        places = new ArrayList<>();
        for(int i=0;i<house.heatedPlaces.size();i++)
            places.add(house.heatedPlaces.get(i).getName());

        actTemperatures = new ArrayList<>();
        for(int i=0;i<house.heatedPlaces.size();i++)
            actTemperatures.add(house.heatedPlaces.get(i).getActTemp());

        reqTemperatures = new ArrayList<>();
        for(int i=0;i<house.heatedPlaces.size();i++)
            reqTemperatures.add(house.heatedPlaces.get(i).getReqTemp());

        status = new ArrayList<>();
        for(int i=0;i<house.heatedPlaces.size();i++)
            status.add(house.heatedPlaces.get(i).getStatus());
    }
}
