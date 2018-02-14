package com.alex.smartHome.src.controllers;

import com.alex.smartHome.src.communication.Protocol;
import com.alex.smartHome.src.data.*;
import com.alex.smartHome.src.model.*;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by cosma on 10.05.2017.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/login")
public class LoginController {
//    public static List<ClientThread> clients;
    public static House house;
    private boolean isData;

    public LoginController(){
        //checkData();
    }

    private void checkData(){

        if(new UsersDAO(new Configuration().configure().buildSessionFactory()).adminCheck()) {
            isData = true;
            List<HeatedObj> heatedPlaces;
            heatedPlaces = new RoomsDAO(new Configuration().configure().buildSessionFactory()).findAll();
            heatedPlaces.addAll(new BoilersDAO(new Configuration().configure().buildSessionFactory()).findAll());
            house = new House(heatedPlaces,
                    new HeatingSourceDAO(new Configuration().configure().buildSessionFactory()).find(),
                    new RelaysDAO(new Configuration().configure().buildSessionFactory()).findProtection());
        }
        else{
            AdminUser admin = new AdminUser(1, "123");
            new DataBase().addUser(admin);
            house = new House(admin);
            isData = false;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Protocol login(@RequestBody int id,@RequestBody String pass){
        UsersDAO ug = new UsersDAO(new Configuration().configure().buildSessionFactory());
        User user = ug.find(id);

        if(user != null && user.getPassword().equals(pass)) {
//            ClientThread t = new ClientThread(this);
//            clients.add(t);
//            t.start();
            if (user.getClass().getName().equals("com.alex.smartHome.src.model.AdminUser")) {
                return Protocol.ADMIN_USER;
            } else {
                return Protocol.REGULAR_USER;
            }
        }
            return Protocol.NO_USER;
    }
}
