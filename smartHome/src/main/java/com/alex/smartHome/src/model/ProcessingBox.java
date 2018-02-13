package com.alex.smartHome.src.model;

public class ProcessingBox extends Thread {
	
	public House house;
	public boolean canRun, running;
	
	public ProcessingBox(House house) {
		this.house = house;
		canRun = checkIfCanRun();
		running = false;
	}

	@SuppressWarnings("static-access")
	public synchronized void run()
	{
		int i;
		canRun = checkIfCanRun();
		while(canRun && running)
		{
			//if(canRun&&running)
			//{
				System.out.println("o iteratie");//test rulare thread
				boolean ok=true;
				for(i=0;i<house.getHPNumber();i++)
				{
					if(house.getHPTemp(i)>=house.getHPReqTemp(i) && house.getHPStatus(i) == true)
						house.switchOffHeatingAt(i);
					else
						if(house.getHPTemp(i) < house.getHPReqTemp(i) && house.getHPStatus(i) == false && house.switchOnHeatingAt(i))
							ok=false;
				}
				house.chgProtectionState(ok);
				if(ok)
					house.switchOffCentralHeating();
				else
					house.switchOnCentralHeating();
			//}
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		prepareForSwitchOff();
	}

	private void prepareForSwitchOff(){
		house.protect.setState(true);
		for(int i=0;i<house.getHPNumber();i++){
			house.switchOffHeatingAt(i);
		}
	}
	
	private boolean checkIfCanRun()
	{
		if(house.getHPNumber()>=1)
			return true;
		return false;
	}

}
