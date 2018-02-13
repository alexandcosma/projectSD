package com.alex.smartHome.src.communication;

/**
 * Created by cosma on 19.05.2017.
 */
public enum Protocol {
    ADMIN_USER (1),
    REGULAR_USER (2),
    NO_USER (5),
    DATA_UPDATE (6),
    LOGIN (7),
    CHG_TEMPERATURE (8),
    ADD_HEATING_SOURCE (9),
    ADD_PROTECTION (10),
    ADD_ROOM(11),
    ADD_BOILER (12),
    DELETE_HP(13),
    ADD_USER(14),
    DELETE_USER(15),
    SWITCH_ON(16),
    SWITCH_OFF(17)
    ;

    private final int code;

    Protocol(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
