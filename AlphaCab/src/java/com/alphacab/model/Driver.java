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
    
    public Driver(String username, String password, String role, String dName, String registration) {
        super(username, password, role);
        name = dName;
        this.registration = registration;
    }

    public Driver(int id, String username, String password, String role) {
        super(id, username, password, role);
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
    
}
