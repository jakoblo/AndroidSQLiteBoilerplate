package com.example.data;

/**
 * Created by fleischbank on 30.12.2016.
 */

public class Route {

    private int id;
    private String name;
    private String altitude;
    private String level;

    // empty constructor
    public Route() {
    }

    // constructor - no id
    public Route(String name, String altitude, String level) {
        this.name = name;
        this.altitude = altitude;
        this.level = level;
    }

    // constructor - no id
    public Route(int id, String name, String altitude, String level) {
        this.id = id;
        this.name = name;
        this.altitude = altitude;
        this.level = level;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAltitude() {
        return this.altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


}
