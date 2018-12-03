/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author marcus, Trym
 */
public class Demand {

    private final int id;
    private final String name;
    private final String address;
    private final String destination;
    private final Date date;
    private final Time time;
    private final String status;
    private final int customerid;

    Demand(int id, String name, String address, String destination, Date date, Time time, String status, int customerid) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.status = status;
        this.customerid = customerid;
    }

    public Journey makeJourney(Driver driver, double distance, double cost){
        Journey journey = new Journey(this, driver, distance, cost);
        return journey;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public int getCustomerid() {
        return customerid;
    }

}
