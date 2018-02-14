package com.alex.smartHome.src.controllers;

import com.alex.smartHome.src.model.AdminUser;

/**
 * Created by cosma on 10.05.2017.
 */
public class AdminViewController {
    private AdminUser admin;

    public AdminViewController(AdminUser ad){
        admin = ad;
    }

    public void setTemperature(String place, float newTemp){
        admin.setTemperatureTo(place, newTemp);
    }

    public void addHeatingSource(String name, int pinId){
        admin.changeHeatingSource(name, pinId);
    }

    public void addProtection(int pinId) {
        admin.addProtection(pinId);
    }

    public void addRoom(String name, int sPin, int rPin){
        admin.addNewRoom(name, sPin, rPin);
    }

    public void addBoiler(String name, int sPin, int rPin){
        admin.addNewBoiler(name, sPin, rPin);
    }

    public void deleteHp(String hp){
        admin.deleteHeatedPlace(hp);
    }

    public void addUser(int id, String pass){
        admin.addUser(id, pass);
    }

    public void  deleteUser(int id){
        admin.removeUser(id);
    }

    public void swOn(){
        admin.switchOnAutomation();
    }

    public void swOff(){
        admin.switchOffAutomation();
    }

    public void genPdf(String place){
        admin.genPdfReport(place);
    }

    public void genCsv(String place){
        admin.genCsvReport(place);
    }
}
