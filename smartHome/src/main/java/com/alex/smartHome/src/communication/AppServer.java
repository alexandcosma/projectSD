package com.alex.smartHome.src.communication;

import controller.LoginController;
import view.Login;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by cosma on 17.05.2017.
 */
public class AppServer extends Thread{
    public final int port = 12;
    public ServerSocket serverSocket;
    public List<ClientThread> clients;

    public static LoginController loginController;

    public AppServer() throws IOException{
        serverSocket = new ServerSocket(port);
        clients = new ArrayList<>();
    }

    public void run(){
        while(true) {
            try {
                System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
                Socket client = serverSocket.accept();

                try{
                    ClientThread t = new ClientThread(client, loginController);
                    clients.add(t);
                    t.start();
                }catch(Exception e){
                    e.printStackTrace();
                }

                System.out.println("Just connected to " + client.getRemoteSocketAddress());

            }catch(Exception e) {
                System.out.print("Something went wrong with the server...");
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String args[]){

        try {
            AppServer t = new AppServer();
            Login login = new Login(t);
            login.setVisible(true);
            loginController = login.controller;
            t.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateDataClients() {
        for(int i=0;i<clients.size();i++){
            clients.get(i).sendAnswer(Protocol.DATA_UPDATE.getCode());
        }
    }
}
