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
    
    public Customer(int id, String username, String password, String role, String cName, String address) {
        super(id, username, password, role);
        name = cName;
        this.address = address;
    }
    
    public Customer(String username, String password, String role, String cName, String address) {
        super(username, password, role);
        name = cName;
        this.address = address;
    }
    
    public Customer(int id, String username, String password, String role) {
        super(id, username, password, role);
    }
    
//    public Customer(int id, String username, String password, String role) {
//        super(id, username, password, role);
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
