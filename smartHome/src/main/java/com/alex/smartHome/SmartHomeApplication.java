package com.alex.smartHome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SmartHomeApplication {
//	public static List<ClientThread> clients;

	public static void main(String[] args) {
//		LoginController.clients = new ArrayList<>();
//		clients = LoginController.clients;
		SpringApplication.run(SmartHomeApplication.class, args);
	}

//	public static void updateDataClients() {
//		for(int i=0;i<clients.size();i++){
//			clients.get(i).sendAnswer(Protocol.DATA_UPDATE.getCode());
//		}
//	}
}
