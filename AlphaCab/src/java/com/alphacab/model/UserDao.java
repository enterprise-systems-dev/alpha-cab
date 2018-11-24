/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paul, Trym
 */
public class UserDao {

    private Connection connection;

    public UserDao() {
    }
    
    public void connect(Connection conn) {
        this.connection = conn;
    }
    
    public boolean saveUser(User user) {
//        
//        int rowsUpdated;
//        
//        String query = "INSERT INTO Users (username, password, role) VALUES (?, ?, ?)";
//        
//        try {
//            PreparedStatement statement = this.connection.prepareStatement(query);
//            statement.setString(1, user.getUsername().trim());
//            statement.setString(2, user.getPassword().trim());
//            statement.setString(3, user.getRole());
//            
//            rowsUpdated = statement.executeUpdate();
//            
//            ResultSet userId = statement.getGeneratedKeys();
//            
//            /*
//            * savedToClassTable gets its value from the abstract method 
//            * save() which is implemented by each
//            * subclass, callable from a User object, that saves the user
//            * to the relevant subclass data table, i.e.
//            * User user = new Admin(etc..);
//            * user.save() then returns true if the query to add to
//            * admin table is successful
//            */
//            
//            boolean savedToClassTable = false;
//            
//            if(userId.next()) {
//                user.setId(userId.getInt(1));
//                savedToClassTable = user.save(this.connection);
//            }
//            if (rowsUpdated > 0 && savedToClassTable) {
//                return true;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
        return true;
    }
    
    public boolean deleteUser(User user) {
        return true;
    }
    
    public boolean editUser(User user) {
        return true;
    }
    
    //???
    public boolean doesUserExist(String uname, String pword, Connection c) throws SQLException {
        /*
        * NEEDS TO BE UPDATED TO ACCOMPANY THE CHANGES TO CLASSES, maybe not?
        */
        ResultSet resultSet;
        
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        
        try {
//            PreparedStatement statement = this.connection.prepareStatement(query);
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, uname);
            statement.setString(2, pword);
            resultSet = statement.executeQuery();
            
            if(resultSet.next()) {
//                user.setId(resultSet.getInt(1));
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public List<User> getAllDrivers() throws SQLException {
        
        List<User> userList = new ArrayList();
               
        String query = "SELECT * FROM users WHERE role = ?";
        
        PreparedStatement statement = this.connection.prepareStatement(query);
        
        statement.setString(1, "driver");
                
        ResultSet resultSet = statement.executeQuery();
        
        User u;
        
        while(resultSet.next()){
                
                u = new Driver(resultSet.getInt("ID"),
                        resultSet.getString("USERNAME"),
                        resultSet.getString("PASSWORD"),
                        resultSet.getString("ROLE"));
                
            userList.add(u);
        }
        
        return userList;
    }
    
    public List<User> getAllCustomers() throws SQLException {
        
        List<User> userList = new ArrayList();
        
        String query = "SELECT * FROM users WHERE role = ?";
        
        PreparedStatement statement = this.connection.prepareStatement(query);
        
        statement.setString(1, "customer");
        
        ResultSet resultSet = statement.executeQuery();
        
        User u;
        
        while(resultSet.next()){
                
                u = new Customer(resultSet.getInt("ID"),
                        resultSet.getString("USERNAME"),
                        resultSet.getString("PASSWORD"),
                        resultSet.getString("ROLE"));
                
            userList.add(u);
        }
        
        return userList;
    }

    public List<User> getAllUsers() throws SQLException {
        
        List<User> userList = new ArrayList();
        
        String query = "SELECT * FROM users";  //???
        
        PreparedStatement statement = this.connection.prepareStatement(query);
        
        ResultSet resultSet = statement.executeQuery();
        
        User u = null;
        
        int newId;
        String newUname;
        String newPword;
        String newRole;
        
        while(resultSet.next()){
            
                newId = resultSet.getInt("ID");
                newUname = resultSet.getString("USERNAME");
                newPword = resultSet.getString("PASSWORD");
                newRole = resultSet.getString("ROLE");
                
                switch(newRole) {
                case "admin":
                    u = new Admin(newId, newUname, newPword, newRole);
                    break;
                case "customer":
                    u = new Customer(newId, newUname, newPword, newRole);
                    break;
                case "driver":
                    u = new Driver(newId, newUname, newPword, newRole);
                    break;
                default:
                    //Error!!!
                }
                
            userList.add(u);
        }
        
        return userList;
    }
}