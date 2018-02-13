package com.alex.smartHome.src.controller;

import com.alex.smartHome.src.communication.AppServer;
import com.alex.smartHome.src.communication.Protocol;
import com.alex.smartHome.src.model.*;
import data.*;
import view.*;

/**
 * Created by cosma on 10.05.2017.
 */
public class LoginController {
    public House house;
    private boolean isData;
    public AppServer appServer;

    public LoginController(AppServer as){
        appServer = as;
        checkData();
    }

    public void checkData(){

        if(new UsersGateway().adminCheck()) {
            isData = true;
            house = new House(appServer, new HeatedPlacesGateway().findAll(), new HeatingSourceGateway().find(), new RelaysGateway().findProtection());
        }
        else{
            AdminUser admin = new AdminUser(1, "123");
            new DataBase().addUser(admin);
            house = new House(admin, appServer);
            isData = false;
        }
    }

    public void login(int id, String pass){
        if(!isData){
            if(house.administrator.getId() == id && house.administrator.getPassword().equals(pass)) {
                (new AdminView(house.administrator)).setVisible(true);
                (new Monitor(house)).setVisible(true);
                new WeatherView().setVisible(true);
            }
        }
        else{
            UsersGateway ug = new UsersGateway();
            User user = ug.find(id);

            if(user.getPassword().equals(pass))
                if(user.getClass().getName().equals("model.AdminUser")) {
                    house.administrator = (AdminUser)user;
                    house.administrator.setHouse(house);
                    new AdminView(house.administrator).setVisible(true);
                    new Monitor(house).setVisible(true);
                    new WeatherView().setVisible(true);
                }else{
                    house.usersList.add((RegularUser)user);
                    house.usersList.get(house.usersList.size()-1).setHouse(house);
                    new UserView(house.usersList.get(house.usersList.size()-1)).setVisible(true);
                    new Monitor(house).setVisible(true);
                    new WeatherView().setVisible(true);
                }
        }
    }

    public Protocol remoteLogin(int id, String pass){
        UsersGateway ug = new UsersGateway();
        User user = ug.find(id);

        if(user != null && user.getPassword().equals(pass))
            if(user.getClass().getName().equals("model.AdminUser")) {
                return Protocol.ADMIN_USER;
            }else{
                return Protocol.REGULAR_USER;
            }

            return Protocol.NO_USER;
    }
}
