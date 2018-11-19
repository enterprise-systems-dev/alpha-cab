/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.model;

/**
 *
 * @author Paul
 */
public class Driver extends User {
    
    private int id;
    
    private String name;
    
    private String registration;
    
    private int userId;
    
    public Driver() {
    }

    public Driver(String username, String password, String role, String name, String registration) {
        super(username, password, role);
        this.name = name;
        this.registration = registration;
    }



    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
}
