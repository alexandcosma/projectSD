package com.alex.smartHome.src.model;

import com.alex.smartHome.src.communication.AppServer;
import com.alex.smartHome.src.physicalEq.Relay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cosma on 01.05.2017.
 */
public class House {
    public List<HeatedObj> heatedPlaces;
    public HeatingObj heatingSource;
    public List<RegularUser> usersList;
    public AdminUser administrator;
    public Relay protect;
    public ProcessingBox processingBox;
    public AppServer appServer;

    public House(List<HeatedObj> heatedObjList, HeatingObj heatingObj, List<RegularUser> usersList, AdminUser admin, Relay protect) {
        heatedPlaces = heatedObjList;
        heatingSource = heatingObj;
        this.usersList = usersList;
        administrator = admin;
        setHouseToAllUsers();
        this.protect = protect;
        processingBox = new ProcessingBox(this);
    }

    public House(AppServer as, List<HeatedObj> heatedObjList, HeatingObj heatingObj, Relay protect) {
        appServer = as;
        heatedPlaces = heatedObjList;
        heatingSource = heatingObj;
        this.usersList = new ArrayList<>();
        this.protect = protect;
        //processingBox = new ProcessingBox(this);
    }

    private void setHouseToAllUsers() {
        for(int i=0;i<usersList.size();i++)
            usersList.get(i).setHouse(this);
        administrator.setHouse(this);
    }

    public House(AdminUser admin, AppServer as){
        appServer = as;
        heatedPlaces = new ArrayList<HeatedObj>();
        usersList = new ArrayList<RegularUser>();
        administrator = admin;
        administrator.setHouse(this);
        protect=null;
        //processingBox = new ProcessingBox(this);
    }

    public HeatedObj getHeatedPlaceByName(String name){
        HeatedObj ho = null;
        for(int i=0;i<heatedPlaces.size() && ho == null;i++)
            if(heatedPlaces.get(i).getName().equals(name))
                ho = heatedPlaces.get(i);
        return ho;
    }

    public String getHPName(int index)
    {
        return heatedPlaces.get(index).getName();
    }

    public int getHPNumber()
    {
        return heatedPlaces.size();
    }

    public float getHPTemp(int index)
    {
        return heatedPlaces.get(index).getActTemp();
    }

    public float getHPReqTemp(int index)
    {
        return heatedPlaces.get(index).getReqTemp();
    }

    public boolean checkCentralHeatingStatus()
    {
        return heatingSource.getStatus();
    }

    public boolean switchOnCentralHeating()
    {
        boolean ok=false;
        if(!checkCentralHeatingStatus())
        {
            for(int i=0;i<heatedPlaces.size();i++)
            {
                if(heatedPlaces.get(i).getStatus())
                    ok=heatedPlaces.get(i).getStatus();
            }
            if(ok)
            {
                ok=heatingSource.tunrOn();
            }
            return ok;
        }
        return true;
    }

    public boolean switchOffCentralHeating()
    {
        if(checkCentralHeatingStatus())
            return heatingSource.turnOff();
        return true;
    }

    public boolean switchOnHeatingAt(int index)
    {
        if(!heatedPlaces.get(index).getStatus())
        {
            Boolean b = heatedPlaces.get(index).turnOnHeating();
            appServer.updateDataClients();
            return b;
        }
        return true;
    }

    public boolean switchOffHeatingAt(int index)
    {
        boolean ok=true;
        for(int i=0;i<heatedPlaces.size();i++)
            if(heatedPlaces.get(i).getStatus()&&i!=index)
                ok=false;
        if(ok)
            if(protect.getState()&&!heatingSource.getStatus()) {
                Boolean b = heatedPlaces.get(index).turnOffHeating();
                appServer.updateDataClients();
                return b;
            }
            else
                return false;
        ok = heatedPlaces.get(index).turnOffHeating();
        appServer.updateDataClients();
        return ok;
    }

    public boolean chgProtectionState(boolean state)
    {
        if(state!=protect.getState())
        {
            return protect.setState(state);
        }
        return true;
    }

    //////////////////////////////////////////////////

    public boolean getCHState()
    {
        return heatingSource.getStatus();
    }

    public boolean getProtectionStatus()
    {
        return protect.getState();
    }

    public boolean createHeatingObj(String name, int pin)
    {
        if(heatingSource==null)
        {
            heatingSource=new HeatingObj(name, pin);
            return true;
        }
        else
            return false;
    }

    public void createProtectionCircuit(int pin)
    {
        protect=new Relay(pin);
    }

    public boolean addRoom(String name, int sPin, int rPin)
    {
        if(heatingSource!=null&&protect!=null)
        {
            Room r=new Room(name, sPin, rPin);
            heatedPlaces.add(r);
            return true;
        }
        return false;
    }

    public boolean addBoiler(String name, int sPin, int rPin)
    {
        if(heatingSource!=null&&protect!=null)
        {
            Boiler b=new Boiler(name, sPin, rPin);
            heatedPlaces.add(b);
            return true;
        }
        return false;
    }

    public boolean getHPStatus(int index)
    {
        return heatedPlaces.get(index).getStatus();
    }

    public boolean setHPReqTemp(float tmp, int index)
    {
        return heatedPlaces.get(index).setReqTemp(tmp);
    }

    public void setHPActTemp(int index, float t)//TODO metoda se va desfiinta la adaugarea senzorilor fizici la proiect
    {
        heatedPlaces.get(index).setActTmp(t);
    }

    public void updateUsers() {
        appServer.updateDataClients();
    }
}
