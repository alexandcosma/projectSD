package com.alex.smartHome.src.model;

import com.alex.smartHome.src.physicalEq.Relay;
import com.alex.smartHome.src.physicalEq.Sensor;

public class Room extends HeatedObj {

	public Room(String n, int sensPinNumber,int rlPinNumber) {
		super(n, sensPinNumber, rlPinNumber);
	}

	public Room(HeatedObj heatedObj)
	{
		name = heatedObj.getName();
		actTemp = 0;
		this.reqTemp = heatedObj.getReqTemp();
		sensor = new Sensor(heatedObj.getSensorPin());
		rl = new Relay(heatedObj.getRelayPin());
	}

	public boolean setReqTemp(float tmp) {
		if(tmp>35)
			return false;
		this.reqTemp=tmp;
		return true;
	}

	
}
