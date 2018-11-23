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
    
    private int id;
    
    private String name;
    
    private static String ROLE = "Driver";
    
    private String registration;
    
    private int userId;
    

    public Driver(int id, String username, String password, String name, String registration) {
        super(id, username, password);
        this.name = name;
        this.registration = registration;
    }
    
    //Default constructor
    
    public Driver(int id, String username, String password){
        super(id, username, password);
        this.name = "";
        this.registration = "";
    }

    @Override
    public boolean save(){
        
        return false;
    }
    
    @Override
    public String getRole(){
        return ROLE;
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
