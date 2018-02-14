package com.alex.smartHome.src.model;

import com.alex.smartHome.src.physicalEq.Relay;

public class HeatingObj {

	private String name;
	private int relayPin;
	private Relay rel;
	
	public HeatingObj(String name, int relPin)
	{
		this.name=name;
		rel=new Relay(relPin);
		relayPin = relPin;
	}

	public HeatingObj() {
	}

	public boolean equals(Object ho){
		return ((HeatingObj)ho).name.equals(this.name) && ((HeatingObj)ho).rel.equals(this.rel);
	}
	
	public String getName()
	{
		return new String(name);
	}
	public void setName(String name){
		this.name = name;
	}

	public int getRelPin(){
		return rel.getPin();
	}
	
	public boolean tunrOn()
	{
		boolean t;
		t=rel.setState(true);
		return t;
	}
	
	public boolean turnOff()
	{
		boolean t;
		t=rel.setState(false);
		return t;
	}
	public int getRelayPin() {
		return relayPin;
	}

	public void setRelayPin(int relayPin) {
		this.relayPin = relayPin;
	}
	
	public boolean getStatus()
	{
		return rel.getState();
	}
}
