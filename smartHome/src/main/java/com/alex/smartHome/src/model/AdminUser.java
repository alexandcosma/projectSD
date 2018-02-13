package com.alex.smartHome.src.model;

import com.alex.smartHome.src.data.DataBase;
import com.alex.smartHome.src.model.reports.Report;
import com.alex.smartHome.src.model.reports.ReportFactory;
import com.alex.smartHome.src.physicalEq.Relay;

/**
 * Created by cosma on 26.04.2017.
 */
public class AdminUser extends User {

    public AdminUser(int id, String password, House house){
        super(id, password, house);
    }

    public AdminUser(int id, String password){
        super(id, password);
    }

    public boolean addHeatingSource(String name, int pinId){
        if(house.heatingSource != null)
            return false;
        else {
            house.heatingSource = new HeatingObj(name, pinId);
            new DataBase().modifyHeatingSource(house.heatingSource);
        }
        return true;
    }

    public boolean addProtection(int pinId){
        if(house.protect != null)
            return false;
        else {
            house.protect = new Relay(pinId);
            new DataBase().addProtection(house.protect);
        }
        return true;
    }

    public void changeHeatingSource(String name, int pinId){
        house.heatingSource = new HeatingObj(name, pinId);
        new DataBase().modifyHeatingSource(house.heatingSource);
    }

    public void addNewRoom(String name, int sPin, int rPin){
        Room room = new Room(name, sPin, rPin);
        house.heatedPlaces.add(room);
        new DataBase().addHeatedPlace(room);
    }

    public void addNewBoiler(String name, int sPin, int rPin){
        Boiler boiler = new Boiler(name, sPin, rPin);
        house.heatedPlaces.add(boiler);
        new DataBase().addHeatedPlace(boiler);
    }

    public void deleteHeatedPlace(String name){
        HeatedObj ho = house.getHeatedPlaceByName(name);
        if(ho != null) {
            house.heatedPlaces.remove(ho);
            new DataBase().removeHeatedPlace(ho);
        }
    }

    public void addUser(int id, String pass){
        RegularUser user = new RegularUser(id, pass, house);
        house.usersList.add(user);
        new DataBase().addUser(user);
    }

    public void removeUser(int id){
        for(int i=0;i<house.usersList.size();i++)
            if(house.usersList.get(i).id == id){
                new DataBase().removeUser(house.usersList.get(i).getId());
                house.usersList.remove(i);
                break;
            }
    }

    public void switchOnAutomation(){
        house.processingBox = new ProcessingBox(house);
        house.processingBox.running = true;
        if(!house.processingBox.isAlive())
            house.processingBox.start();
    }

    public void switchOffAutomation(){
        house.processingBox.running = false;
    }

    public void genPdfReport(String placeName){
        Report rep = (new ReportFactory()).getReport("PDF");
        rep.write(placeName);
    }

    public void genCsvReport(String placeName){
        Report rep = (new ReportFactory()).getReport("CSV");
        rep.write(placeName);
    }
}
