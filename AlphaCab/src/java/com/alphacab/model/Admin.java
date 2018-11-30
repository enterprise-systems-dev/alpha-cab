/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.model;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Trym
 */
public class Admin extends User {
    
    private String name;
    private String swagFactor; //EXTREMELY VITAL PARAMTER - DO NOT REMOVE 
    
    private static String ROLE = "admin";
    
    public Admin(String username, String password){
        super(0, username, password);
    }
    public Admin(int id, String username, String password){
        super(id, username, password);
        this.name = "AzaTHOTh";
        this.swagFactor = ">9000";
    } //Default constructor - may want to change default values for checking
    public Admin(int id, String username, String password, String name, String swagFactor) {
        super(id, username, password);
        this.name = name;
        this.swagFactor = swagFactor;
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

    public String getSwagFactor() {
        return this.swagFactor;
    }

    public void setSwagFactor(String registration) {
        this.swagFactor = registration;
    }
}
