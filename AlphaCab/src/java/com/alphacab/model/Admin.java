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
    
//    private int id;
    
//    private String name;
    
//    private String swagFactor;
//    private static String ROLE = "Admin";
//    
//    private int userId;

    public Admin(String username, String password, String role) {
        super(username, password, role);
    }
    
    public Admin(int id, String username, String password, String role) {
        super(id, username, password, role);
//        name = "admin";
    }

//    public Admin(String username, String password, String name, String swagFactor) {
//        super( username, password);
//        this.name = name;
//        this.swagFactor = swagFactor;
//    }
    
//    //Default constructor, may want to change default values for checking
//    public Admin(String username, String password){
//        super(username, password);
//        this.name = "Azathoth";
//        this.swagFactor = ">9000";
//    }

//    public boolean save(Connection connection){
//        /*
//        * 
//        * ACCESS THE ADMIN TABLE AND SAVE INSTANCE
//        * 
//        */
//        
//        int rowsUpdated = 0;
//        
//        String adminsQuery = "INSERT INTO Admins (name, swagfactor, userid) VALUES (?, ?, ?)";
//        
//        try {
//            PreparedStatement userStatement = connection.prepareStatement(adminsQuery);
//            ResultSet userId = userStatement.getGeneratedKeys();
//            
//            if(userId.next()) {
//                this.setId(userId.getInt(1));
//            }
//            
//            userStatement.setString(1, this.getName());
//            userStatement.setString(2, this.getSwagFactor());
//            userStatement.setInt(3, this.getUserId());
//            
//            rowsUpdated = userStatement.executeUpdate();
//            
//            return true;
//        } catch(SQLException ex) {
//            
//        }
//        
//        return false;
//        
//    }
//
//    @Override
//    public String getRole(){
//        return ROLE;
//    }

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
//    public String getSwagFactor() {
//        return this.swagFactor;
//    }
//
//    public void setSwagFactor(String registration) {
//        this.swagFactor = registration;
//    }
//
//    public int getUserId() {
//        return this.userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
}
