package com.alex.smartHome.src.model.test;

//import com.alex.smartHome.src.model.*;
//import com.alex.smartHome.src.physicalEq.Relay;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;

/**
 * Created by cosma on 04.05.2017.
 */
public class UserTest {
//    List<HeatedObj> heatedObjList;
//    HeatingObj heatingObj;
//    List<RegularUser> usersList;
//    User admin;
//    Relay protect;
//    House house;
//
//    public void initializeData(){
//        heatedObjList = new ArrayList<>();
//        heatedObjList.add(new Room("room1", 1, 2));
//        heatedObjList.add(new Room("room2", 3, 4));
//        heatedObjList.add(new Boiler("boiler1", 5, 6));
//
//        heatingObj = new HeatingObj("GasBased", 7);
//
//        usersList = new ArrayList<>();
//        usersList.add(new RegularUser(2, "123"));
//        usersList.add(new RegularUser(3, "123"));
//
//        admin = new AdminUser(1, "123");
//
//        protect = new Relay(8);
//
//        house = new House(heatedObjList, heatingObj, usersList, (AdminUser)admin, protect);
//    }
//
//    @Test
//    public void setTempTest(){
//        initializeData();
//        house.administrator.setTemperatureTo("room1", 18.5f);
//        house.administrator.setTemperatureTo("room2", 40f);
//        house.administrator.setTemperatureTo("boiler1", 90f);
//
//        assertTrue(house.getHeatedPlaceByName("room1").getReqTemp() == 18.5f && house.getHeatedPlaceByName("room2").getReqTemp() != 40f && house.getHeatedPlaceByName("boiler1").getReqTemp() != 90f);
//    }
//
//    @Test
//    public void addHeatingSourceTest(){
//        User admin = new AdminUser(1, "123");
//        House house = new House((AdminUser)admin, null);
//
//        house.administrator.addHeatingSource("GasBased", 1);
//        assertTrue(house.heatingSource.getName().equals("GasBased") && house.heatingSource.getRelPin() == 1);
//    }
//
//    @Test
//    public void addProtectionTest(){
//        User admin = new AdminUser(1, "123");
//        House house = new House((AdminUser)admin, null);
//
//        house.administrator.addProtection(1);
//        assertTrue(house.protect.getPin()==1);
//    }
//
//    @Test
//    public void changeHeatingSourceTest(){
//        initializeData();
//
//        house.administrator.changeHeatingSource("Test", 5);
//
//        assertTrue(house.heatingSource.getName().equals("Test") && house.heatingSource.getRelPin() == 5);
//    }
//
//    @Test
//    public void addNewRoomTest(){
//        initializeData();
//
//        house.administrator.addNewRoom("BabyRoom", 6, 7);
//
//        assertTrue(house.heatedPlaces.get(3).getName().equals("BabyRoom"));
//    }
//
//    @Test
//    public void addNewBoilerTest(){
//        initializeData();
//
//        house.administrator.addNewBoiler("Boiler2", 6, 7);
//
//        assertTrue(house.heatedPlaces.get(3).getName().equals("Boiler2"));
//    }
//
//    @Test
//    public void deleteHeatingPlaceTest(){
//        initializeData();
//
//        house.administrator.deleteHeatedPlace("room2");
//
//        assertTrue(house.heatedPlaces.size() == 2);
//    }
//
//    @Test
//    public void addUserTest(){
//        initializeData();
//
//        house.administrator.addUser(6, "123");
//
//        assertTrue(house.usersList.get(2).getId() == 6);
//    }
//
//    @Test
//    public void removeUserTest(){
//        initializeData();
//
//        house.administrator.removeUser(2);
//
//        assertTrue(house.usersList.get(0).getId() == 3);
//    }
}
