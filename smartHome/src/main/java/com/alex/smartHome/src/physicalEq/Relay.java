package com.alex.smartHome.src.physicalEq;

public class Relay {

	private int pinNr;
	private boolean state;
	
	public Relay(int pinN)
	{
		pinNr=pinN;
		state=false;
	}

	public Relay() {
	}

	public boolean equals(Object rl){
		return ((Relay)rl).pinNr == this.pinNr;
	}
	
	public boolean setState(boolean st)
	{
		state=st;
		return true;//TODO
	}
	
	public boolean getState()
	{
		return state;
	}
	
	public int getPinNr()
	{
		return pinNr;
	}

	public void setPinNr(int pin){
		this.pinNr = pin;
	}

}
