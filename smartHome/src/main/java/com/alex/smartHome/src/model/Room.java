package com.alex.smartHome.src.model;

import data.DataBase;

public class Room extends HeatedObj {

	public Room(String n, int sensPinNumber,int rlPinNumber) {
		super(n, sensPinNumber, rlPinNumber);
		
	}

	public boolean setReqTemp(float tmp) {
		if(tmp>35)
			return false;
		this.reqTemp=tmp;
		return true;
	}

	
}
