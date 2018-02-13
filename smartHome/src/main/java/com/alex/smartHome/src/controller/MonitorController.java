package com.alex.smartHome.src.controller;

import view.Monitor;

import javax.swing.table.DefaultTableModel;

/**
 * Created by cosma on 10.05.2017.
 */
public class MonitorController extends Thread {
    private Monitor mon;

    public MonitorController(Monitor m){
        mon = m;
        start();
    }

    public void run(){
        while(true){
            reloadTab();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void reloadTab()
	{
		Object[][] o;
		int rows=mon.house.getHPNumber();
		o=new Object[rows][4];

		for(int i=0;i<rows;i++)
		{
			o[i][0]=new String(mon.house.getHPName(i));
			o[i][1]=new Float(mon.house.getHPTemp(i));
			o[i][2]=new Float(mon.house.getHPReqTemp(i));
			if(mon.house.getHPStatus(i))
				o[i][3]=new String("ON");
			else
				o[i][3]=new String("OFF");
		}

		mon.table.setModel(new DefaultTableModel(o,new String[] {"Name", "Act. Temp", "Req. temp", "Status"})
		{
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, Float.class, Float.class, String.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
	}

	public void chgActTmp(String place, float tmp){
        boolean ok = true;
        int i;
        for(i=0;i<mon.house.heatedPlaces.size() && ok;i++){
            if(mon.house.heatedPlaces.get(i).getName().equals(place))
                ok=false;
        }
        if(!ok){
            mon.house.setHPActTemp(i-1, tmp);
        }
    }
}
