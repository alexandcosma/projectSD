package com.alex.smartHome.src.model;

import data.DataBase;

public class Boiler extends HeatedObj {

	public Boiler(String n, int sensorPinNr, int relPinNr) {
		super(n, sensorPinNr, relPinNr);
		
	}

	public boolean setReqTemp(float tmp) {
		if(tmp>85)
			return false;
		this.reqTemp=tmp;
		return true;
	}

	
}
