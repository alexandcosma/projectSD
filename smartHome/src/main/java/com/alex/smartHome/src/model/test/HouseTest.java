package com.alex.smartHome.src.model.test;

import static org.junit.Assert.*;

import com.alex.smartHome.src.model.*;
import com.alex.smartHome.src.physicalEq.Relay;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cosma on 04.05.2017.
 */
public class HouseTest {
    @Test
    public void createHouseWithAdmin() {
        User admin = new AdminUser(1, "123");
        House house = new House((AdminUser)admin, null);
        assertTrue(house.administrator.equals(admin));
    }

    @Test
    public void createHouseFromFile(){
        List<HeatedObj> heatedObjList = new ArrayList<>();
        heatedObjList.add(new Room("room1", 1, 2));
        heatedObjList.add(new Room("room2", 3, 4));
        heatedObjList.add(new Boiler("boiler1", 5, 6));

        HeatingObj heatingObj = new HeatingObj("GasBased", 7);

        List<RegularUser> usersList = new ArrayList<>();
        usersList.add(new RegularUser(2, "123"));
        usersList.add(new RegularUser(3, "123"));

        User admin = new AdminUser(1, "123");

        Relay protect = new Relay(8);

        House house = new House(heatedObjList, heatingObj, usersList, (AdminUser)admin, protect);

        boolean c1 = house.heatedPlaces.size() == 3 && house.getHeatedPlaceByName("room2").equals(heatedObjList.get(1));
        boolean c2 = house.heatingSource.equals(heatingObj);
        boolean c3 = house.usersList.size() == 2 && house.administrator.getHouse() == house;
        for(int i=0;i<house.usersList.size();i++)
            if(house.usersList.get(i).getHouse()!=house)
                c3 = false;
        boolean c4 = house.protect.getPin() == 8;
        assertTrue(c1 && c2 && c3 && c4);
    }
}
