/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.model;

/**
 *
 * @author marcus
 */
public class Demand {

    private final int id;
    private final String name;
    private final String address;
    private final String destination;
    private final String date;
    private final String time;
    private final String status;
    private final int customerid;

    Demand(int id, String name, String address, String destination, String date, String time, String status, int customerid) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.status = status;
        this.customerid = customerid;
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

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public int getCustomerid() {
        return customerid;
    }

}
