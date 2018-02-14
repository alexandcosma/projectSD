package com.alex.smartHome.src.model;

import com.alex.smartHome.src.data.Logger;
import com.alex.smartHome.src.physicalEq.Relay;
import com.alex.smartHome.src.physicalEq.Sensor;

import java.util.Date;

import static java.lang.Math.abs;

public abstract class HeatedObj {

	protected float actTemp;
	protected float reqTemp;
	protected String name;
	protected Sensor sensor;
	protected int sensorPin;
	protected int relayPin;
	protected Relay rl;
	
	public HeatedObj(String n, int sensPinNumber,int rlPinNumber)
	{
		name=new String(n);
		actTemp=0;
		reqTemp=0;
		sensor=new Sensor(sensPinNumber);
		rl=new Relay(rlPinNumber);
		sensorPin = sensPinNumber;
		relayPin = rlPinNumber;
	}

	public HeatedObj(String n, int sensPinNumber,int rlPinNumber, float reqTemp)
	{
		name=n;
		actTemp=0;
		this.reqTemp=reqTemp;
		sensor=new Sensor(sensPinNumber);
		rl=new Relay(rlPinNumber);
		sensorPin = sensPinNumber;
		relayPin = rlPinNumber;
	}
	public HeatedObj(){};

	public boolean equals(Object hpl){
		return ((HeatedObj)hpl).name.equals(this.name) && ((HeatedObj)hpl).sensor.equals(this.sensor) && ((HeatedObj)hpl).rl.equals(this.rl);
	}

	public int getSensorPin(){
		return sensor.getPinNr();
	}

	public int getRelayPin(){
		return rl.getPinNr();
	}
	
	public float getActTemp()
	{
		return actTemp;
	}
	
	public float getReqTemp()
	{
		return reqTemp;
	}
	
	public String getName()
	{
		return new String(name);
	}
	
	public boolean updateActTemp()
	{
		boolean ok;
		ok=sensor.readTemp();
		if(ok) {
			if(abs(sensor.getTemp() - actTemp) >= 0.5) {
				actTemp = sensor.getTemp();
				new Logger().addLog(new Date(), name, Float.toString(actTemp), true);
			}
		}
		return ok;
	}
	
	public boolean turnOnHeating()
	{
		boolean t = false;
		if(!rl.getState()) {
			t = rl.setState(true);
			new Logger().addLog(new Date(), name, "ON", false);
		}
		return t;			
	}
	
	public boolean turnOffHeating()
	{
		boolean t = false;
		if(rl.getState()) {
			t = rl.setState(false);
			new Logger().addLog(new Date(), name, "OFF", false);
		}
		return t;
	}
	
	public boolean getStatus()
	{
		return rl.getState();
	}
	
	public void setActTmp(float t)//TODO se va desfiinta la adaugarea senzorilor fizici
	{
		sensor.setTemp(t);
		updateActTemp();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSensorPin(int sensorPin) {
		this.sensorPin = sensorPin;
	}

	public void setRelayPin(int relayPin) {
		this.relayPin = relayPin;
	}

	public abstract boolean setReqTemp(float tmp);

}
