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
public class Journey extends Demand{
    
    private Driver driver;
    private double distance;
    private double cost;
    

    public Journey(String name, String address, int customerID, String destination, double distance, double cost, Date date, Time time, Driver driver) {
        super(0, name, address, destination, date, time, "Processed", customerID);
        this.driver = driver;
        this.distance = distance;
        this.cost = cost;
    }
    public Journey(Demand demand, Driver driver, double distance, double cost){
        this(demand.getName(), 
                demand.getAddress(),
                demand.getCustomerid(),
                demand.getDestination(),
                distance,
                cost,
                demand.getDate(),
                demand.getTime(),
                driver);
    }

    public Driver getDriver(){
        return this.driver;
    }
    
    public void setDriver(Driver newDriver){
        this.driver = newDriver;
    }
    
    public double getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }


    public double getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    
    
    
}
