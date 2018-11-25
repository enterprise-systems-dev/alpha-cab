/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Paul, Trym
 */
public class Driver extends User {
    
//    private int id;
//    
    private String name;
//    
//    private static String ROLE = "Driver";
//    
    private String registration;
//    
//    private int userId;
    
    public Driver(String username, String password, String role, String dName, String registration) {
        super(username, password, role);
        name = dName;
        this.registration = registration;
    }

    public Driver(int id, String username, String password, String role) {
        super(id, username, password, role);
    }
    
//    public Driver(int id, String username, String password, String name, String registration) {
//        super(id, username, password);
//        this.name = name;
//        this.registration = registration;
//    }
//    
//    //Default constructor
//    
//    public Driver(int id, String username, String password){
//        super(id, username, password);
//        this.name = "";
//        this.registration = "";
//    }

//    public boolean save(Connection connection){
//        String driversQuery = "INSERT INTO Drivers (registration, name, userid) VALUES (?, ?, ?)";
//        
//        try {
//            
//            PreparedStatement driversStatement = connection.prepareStatement(driversQuery);
//            driversStatement.setString(1, this.getRegistration());
//            driversStatement.setString(2, this.getName());
//            driversStatement.setInt(3, this.getUserId());
//            
//            driversStatement.executeUpdate();
//            
//            return true;
//        } catch(SQLException ex) {
//            
//        }
//        
//        return false;   
//    }
//    
//    @Override
//    public String getRole(){
//        return ROLE;
//    }
//    
//    public String getName() {
//        return name;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//    
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getRegistration() {
//        return registration;
//    }
//
//    public void setRegistration(String registration) {
//        this.registration = registration;
//    }
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
    
//    public void addJob(Job j) {
//        jobQueue.addLast(j);
//    }
//    
//    public Job removeJob() {
//        return (Job) jobQueue.removeFirst();
//    }
//    
//    public Job getCurrentJob() {
//        return (Job) jobQueue.getFirst();
//    }

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
