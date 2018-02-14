package com.alex.smartHome.src.model;

import com.alex.smartHome.src.physicalEq.Relay;
import com.alex.smartHome.src.physicalEq.Sensor;

public class Boiler extends HeatedObj {

	public Boiler(String n, int sensorPinNr, int relPinNr) {
		super(n, sensorPinNr, relPinNr);
	}

	public Boiler(HeatedObj heatedObj)
	{
		name = heatedObj.getName();
		actTemp = 0;
		this.reqTemp = heatedObj.getReqTemp();
		sensor = new Sensor(heatedObj.getSensorPin());
		rl = new Relay(heatedObj.getRelayPin());
	}

	public Boiler() {
	}

	public boolean setReqTemp(float tmp) {
		if(tmp>85)
			return false;
		this.reqTemp=tmp;
		return true;
	}

	
}
