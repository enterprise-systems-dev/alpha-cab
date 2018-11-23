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
public abstract class UserDao {

    private Connection connection;

    public UserDao() {
    }
    
    public void connect(Connection conn) {
        this.connection = conn;
    }
    
    public boolean saveUser(User user) {
        
        int rowsUpdated = 0;
        
        String query = "INSERT INTO Users (username, password, role) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, user.getUsername().trim());
            statement.setString(2, user.getPassword().trim());
            statement.setString(3, user.getRole());
            rowsUpdated = statement.executeUpdate();
            
            /*
            * savedToClassTable gets its value from the abstract method 
            * save() which is implemented by each
            * subclass, callable from a User object, that saves the user
            * to the relevant subclass data table, i.e.
            * User user = new Admin(etc..);
            * user.save() then returns true if the query to add to
            * admin table is successful
            */
            
            boolean savedToClassTable = user.save(this.connection);
            
            if (rowsUpdated > 0 && savedToClassTable) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean doesUserExist(User user) throws SQLException {
        /*
        * NEEDS TO BE UPDATED TO ACCOMPANY THE CHANGES TO CLASSES, maybe not?
        */
        ResultSet resultSet;
        
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            resultSet = statement.executeQuery();
            
            if(resultSet.next()) {
                user.setId(resultSet.getInt(1));
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    //NEED TO DOUBLE CHECK THAT THE DATA USED IN INSTANTIATION IS CORRECT
    public List<User> getAllUsers() throws SQLException {
        
        List<User> userList = new ArrayList();
        
        Statement statement = this.connection.createStatement();
        
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Users\"");
        
        while(resultSet.next()){
            User u;
            switch(resultSet.getString(3)){
                case("Driver"):
                    u = new Driver(resultSet.getInt(1), resultSet.getString(1), resultSet.getString(2));
                    break;
                case("Customer"):
                    u = new Customer(resultSet.getInt(1), resultSet.getString(1), resultSet.getString(2));
                    break;
                case("Admin"):
                    u = new Admin(resultSet.getInt(1), resultSet.getString(1), resultSet.getString(2));
                    break;
                default:
                    u = null;
                    break;
            }
            userList.add(u);
        }
        
        return userList;
    }
}