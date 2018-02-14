package com.alex.smartHome.src.controllers;

import com.alex.smartHome.src.communication.ClientHouse;
import com.alex.smartHome.src.model.AdminUser;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cosma on 10.05.2017.
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/admin")
public class AdminController {
//    private AdminUser admin;

    public AdminController(){
//        admin = new AdminUser();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getHouseStatus(){
        return new ClientHouse(LoginController.house).toString();
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public void setTemperature(@RequestBody int user,
//                               @RequestBody String password,
//                               @RequestBody String place,
//                               @RequestBody float newTemp){
//        new AdminUser(user, password, LoginController.house).setTemperatureTo(place, newTemp);
//    }

//    @RequestMapping(method = RequestMethod.POST)
//    public void addHeatingSource(@RequestBody int user,
//                                 @RequestBody String password,
//                                 @RequestBody String name,
//                                 @RequestBody int pinId){
//        new AdminUser(user, password, LoginController.house).changeHeatingSource(name, pinId);
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public void addProtection(@RequestBody int user,
//                              @RequestBody String password,
//                              @RequestBody int pinId) {
//        new AdminUser(user, password, LoginController.house).addProtection(pinId);
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public void addRoom(@RequestBody int user,
//                        @RequestBody String password,
//                        @RequestBody String name,
//                        @RequestBody int sPin,
//                        @RequestBody int rPin){
//        new AdminUser(user, password, LoginController.house).addNewRoom(name, sPin, rPin);
//    }

//    @RequestMapping(method = RequestMethod.POST)
//    public void addBoiler(@RequestBody int user,
//                          @RequestBody String password,
//                          @RequestBody String name,
//                          @RequestBody int sPin,
//                          @RequestBody int rPin){
//        new AdminUser(user, password, LoginController.house).addNewBoiler(name, sPin, rPin);
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public void deleteHp(@RequestBody int user,
//                         @RequestBody String password,
//                         @RequestBody String hp){
//        new AdminUser(user, password, LoginController.house).deleteHeatedPlace(hp);
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public void addUser(@RequestBody int user,
//                        @RequestBody String password,
//                        @RequestBody int id,
//                        @RequestBody String pass){
//        new AdminUser(user, password, LoginController.house).addUser(id, pass);
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public void  deleteUser(@RequestBody int user,
//                            @RequestBody String password,
//                            @RequestBody int id){
//        new AdminUser(user, password, LoginController.house).removeUser(id);
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public void swOn(@RequestBody int user,
//                     @RequestBody String password){
//        new AdminUser(user, password, LoginController.house).switchOnAutomation();
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public void swOff(@RequestBody int user,
//                      @RequestBody String password){
//        new AdminUser(user, password, LoginController.house).switchOffAutomation();
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public void genPdf(@RequestBody int user,
//                       @RequestBody String password,
//                       @RequestBody String place){
//        new AdminUser(user, password, LoginController.house).genPdfReport(place);
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public void genCsv(@RequestBody int user,
//                       @RequestBody String password,
//                       @RequestBody String place){
//        new AdminUser(user, password, LoginController.house).genCsvReport(place);
//    }
}
