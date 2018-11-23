/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.model;

/**
 *
 * @author Trym
 */
public class Customer extends User {
    
    private int id;
    
    private String name;
    
    private String address;
    private static String ROLE = "Customer";
    
    private int userId;
    

    public Customer(int id, String username, String password, String name, String address) {
        super(id, username, password);
        this.name = name;
        this.address = address;
    }
    
    //Default constructor
    public Customer(int id, String username, String password){
        super(id, username, password);
        this.name = "";
        this.address = "";
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String registration) {
        this.address = registration;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
}
