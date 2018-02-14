package com.alex.smartHome.src.model;


import com.alex.smartHome.src.data.DataBase;

/**
 * Created by cosma on 26.04.2017.
 */
public class User {

    protected int id;
    protected String password;
    protected House house;

    public User(int id, String password, House house) {
        this.id = id;
        this.password = new String(password);
        this.house = house;
    }

    public User(int id, String password) {
        this.id = id;
        this.password = new String(password);
        this.house = null;
    }

    public User() {
    }

    public boolean equals(Object usr) {
        return ((User) usr).id == this.id && ((User) usr).password.equals(this.password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getPassword() {
        return new String(password);
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public House getHouse() {
        return house;
    }

    public void setTemperatureTo(String placeName, float newTemperature) {
        HeatedObj ho = house.getHeatedPlaceByName(placeName);
        house.updateUsers();
        if (ho != null) {
            ho.setReqTemp(newTemperature);
            new DataBase().modifyTempToHeatedPlace(placeName, newTemperature);
        }
    }
}
