package com.alex.smartHome.src.physicalEq;

public class Sensor  {

	private int pinNr;
	private float temp;
	
	public Sensor(int pinN)
	{
		pinNr=pinN;
		temp=0;
	}

	public Sensor() {
	}

	public boolean equals(Object rl){
		return ((Sensor)rl).pinNr == this.pinNr;
	}

	public void setTemp(float tmp){
		temp = tmp;
	}
	
	public boolean readTemp()
	{
		return true;//TODO
	}
	
	public float getTemp()
	{
		return temp;
	}
	
	public int getPinNr()
	{
		return pinNr;
	}

	public void setPinNr(int nr){
		this.pinNr = nr;
	}
}
