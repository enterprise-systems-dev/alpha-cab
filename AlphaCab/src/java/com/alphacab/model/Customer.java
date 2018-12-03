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
    
    private String name;
    private String address;
    
    private static String ROLE = "customer";
    
    // For guests
    public Customer(String name) {
        super(name);
        this.name = name;
    }
    
    public Customer(String username, String password){
        super(0, username, password);
    }
    public Customer(int id, String username, String password){
        super(id, username, password);
        this.name = "";
        this.address = "";
    } //Default constructor
    public Customer(int id, String username, String password, String name, String address) {
        super(id, username, password);
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String registration) {
        this.address = registration;
    }
}
