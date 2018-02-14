package com.alex.smartHome.src.data;

import com.alex.smartHome.src.model.HeatedObj;
import com.alex.smartHome.src.model.HeatingObj;
import com.alex.smartHome.src.model.User;
import com.alex.smartHome.src.physicalEq.Relay;
import org.hibernate.cfg.Configuration;

/**
 * Created by cosma on 06.05.2017.
 */
public class DataBase {

    public void addHeatedPlace(HeatedObj hp) {
        new SensorsDAO(new Configuration().configure().buildSessionFactory()).insert(hp.getSensorPin());
        new RelaysDAO(new Configuration().configure().buildSessionFactory()).insert(hp.getRelayPin(), false);

        if (hp.getClass().getName().contains("Room")) {
            RoomsDAO hpg = new RoomsDAO(new Configuration().configure().buildSessionFactory());
            hpg.insert(hp);
        } else {
            BoilersDAO hpg = new BoilersDAO(new Configuration().configure().buildSessionFactory());
            hpg.insert(hp);
        }
    }

    public void removeHeatedPlace(HeatedObj hp) {
        if (hp.getClass().getName().contains("Room")) {
            RoomsDAO hpg = new RoomsDAO(new Configuration().configure().buildSessionFactory());
            hpg.delete(hp.getName());
        } else {
            BoilersDAO hpg = new BoilersDAO(new Configuration().configure().buildSessionFactory());
            hpg.delete(hp.getName());
        }

        new SensorsDAO(new Configuration().configure().buildSessionFactory()).delete(hp.getSensorPin());
        new RelaysDAO(new Configuration().configure().buildSessionFactory()).delete(hp.getRelayPin());
    }

    public void modifyTempToHeatedPlace(String hp, float tmp) {
        if (hp.getClass().getName().contains("Room")) {
            RoomsDAO hpg = new RoomsDAO(new Configuration().configure().buildSessionFactory());
            hpg.modifyTemp(hp, tmp);
        } else {
            BoilersDAO hpg = new BoilersDAO(new Configuration().configure().buildSessionFactory());
            hpg.modifyTemp(hp, tmp);
        }
    }

    public void modifyHeatingSource(HeatingObj ho) {
        HeatingSourceDAO hsg = new HeatingSourceDAO(new Configuration().configure().buildSessionFactory());
        hsg.delete();

        new RelaysDAO(new Configuration().configure().buildSessionFactory()).insert(ho.getRelPin(), false);
        hsg.insert(ho);
    }

    public void addUser(User user) {
        UsersDAO ug = new UsersDAO(new Configuration().configure().buildSessionFactory());
        ug.insert(user);
    }

    public void removeUser(int id) {
        UsersDAO ug = new UsersDAO(new Configuration().configure().buildSessionFactory());
        ug.delete(id);
    }

    public void addProtection(Relay rel) {
        RelaysDAO rg = new RelaysDAO(new Configuration().configure().buildSessionFactory());
        rg.deleteProtection();
        rg.insert(rel.getPinNr(), true);
    }
}
