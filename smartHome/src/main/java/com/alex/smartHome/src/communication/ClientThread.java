package com.alex.smartHome.src.communication;

import controller.LoginController;
import model.AdminUser;
import model.RegularUser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by cosma on 18.05.2017.
 */
public class ClientThread extends Thread {
    private Socket socket;
    private LoginController loginController;
    private DataInputStream in;
    private DataOutputStream out;
    private ObjectOutputStream objOut;
    private String[] reqSplit;

    public ClientThread(Socket so, LoginController lc){
        socket = so;
        loginController = lc;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            objOut = new ObjectOutputStream(socket.getOutputStream());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                String req = in.readUTF();
                reqSplit = req.split(" ");
                processNewRequest();
            } catch (IOException e) {
                System.out.println("done exception");
                break;
            }
        }
        System.out.println("done out of loop");
    }

    public void sendAnswer(int ans){
        sendFirstAnswer(ans);
        if(ans == Protocol.ADMIN_USER.getCode() || ans == Protocol.REGULAR_USER.getCode() || ans == Protocol.DATA_UPDATE.getCode()){
                sendSecondAnswer();
        }
    }

    public void sendFirstAnswer(int ans){
        try {
            out.write(ans);
        } catch (IOException e) {
            System.out.println("Error sending response to client");
        }
    }

    public void sendSecondAnswer(){
        ClientHouse clientHouse = new ClientHouse(loginController.house);
        try {
            objOut.writeObject(clientHouse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processNewRequest(){
        if (Integer.parseInt(reqSplit[0]) == Protocol.LOGIN.getCode()) {
            int intAns = loginController.remoteLogin(Integer.parseInt(reqSplit[1]), reqSplit[2]).getCode();
            sendAnswer(intAns);
        }
        else
            if(Integer.parseInt(reqSplit[0]) ==Protocol.CHG_TEMPERATURE.getCode()) {
                new RegularUser(7, "123", loginController.house).setTemperatureTo(reqSplit[1], Float.parseFloat(reqSplit[2]));
                sendAnswer(Protocol.DATA_UPDATE.getCode());
            }
            else
                if(Integer.parseInt(reqSplit[0]) ==Protocol.ADD_HEATING_SOURCE.getCode()){
                    new AdminUser(7, "123", loginController.house).changeHeatingSource(reqSplit[1], Integer.parseInt(reqSplit[2]));
                }
                else
                    if(Integer.parseInt(reqSplit[0]) ==Protocol.ADD_PROTECTION.getCode()){
                        new AdminUser(7, "123", loginController.house).addProtection(Integer.parseInt(reqSplit[1]));
                    }
                    else
                        if(Integer.parseInt(reqSplit[0]) ==Protocol.ADD_ROOM.getCode()){
                            new AdminUser(7, "123", loginController.house).addNewRoom(reqSplit[1], Integer.parseInt(reqSplit[2]), Integer.parseInt(reqSplit[3]));
                            sendAnswer(Protocol.DATA_UPDATE.getCode());
                        }
                        else
                            if(Integer.parseInt(reqSplit[0]) ==Protocol.ADD_BOILER.getCode()){
                                new AdminUser(7, "123", loginController.house).addNewBoiler(reqSplit[1], Integer.parseInt(reqSplit[2]), Integer.parseInt(reqSplit[3]));
                                sendAnswer(Protocol.DATA_UPDATE.getCode());
                            }
                            else
                                if(Integer.parseInt(reqSplit[0]) ==Protocol.DELETE_HP.getCode()){
                                    new AdminUser(7, "123", loginController.house).deleteHeatedPlace(reqSplit[1]);
                                    sendAnswer(Protocol.DATA_UPDATE.getCode());
                                }
                                else
                                    if(Integer.parseInt(reqSplit[0]) == Protocol.ADD_USER.getCode()){
                                        new AdminUser(7, "123", loginController.house).addUser(Integer.parseInt(reqSplit[1]), reqSplit[2]);
                                    }
                                    else
                                        if(Integer.parseInt(reqSplit[0]) == Protocol.DELETE_USER.getCode()){
                                            new AdminUser(7, "123", loginController.house).removeUser(Integer.parseInt(reqSplit[1]));
                                        }
                                        else
                                            if(Integer.parseInt(reqSplit[0]) == Protocol.SWITCH_ON.getCode()){
                                                new AdminUser(7, "123", loginController.house).switchOnAutomation();
                                            }
                                            else
                                                if(Integer.parseInt(reqSplit[0]) == Protocol.SWITCH_OFF.getCode()){
                                                    new AdminUser(7, "123", loginController.house).switchOffAutomation();
                                                }
    }
}
