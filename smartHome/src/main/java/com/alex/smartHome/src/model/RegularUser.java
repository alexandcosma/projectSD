package com.alex.smartHome.src.model;

/**
 * Created by cosma on 26.04.2017.
 */
public class RegularUser extends User {

    public RegularUser(int id, String password, House house){
        super(id, password, house);
    }

    public RegularUser(int id, String password){
        super(id, password);
    }
}
