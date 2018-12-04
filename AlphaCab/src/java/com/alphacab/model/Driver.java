/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.model;

/**
 *
 * @author Paul, Trym
 */
public class Driver extends User {
    
    private String name;
    private String registration;
    private boolean busy;
    private int customerID;
    
    private static String ROLE = "driver";
       
    public Driver(String username, String password){
        super(0, username, password);
    }
    public Driver(int id, String username, String password){
        super(id, username, password);
        this.name = "";
        this.registration = "";
    } //Default constructor
    public Driver(int id, String username, String password, String registration, String name) {
        super(id, username, password);
        this.name = name;
        this.registration = registration;
    }
    
    @Override
    public String getRole(){
        return ROLE;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
}
