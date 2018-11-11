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
 * @author Paul
 */
public class UserDao {

    private Connection connection;

    public UserDao() {
    }
    
    public void connect(Connection conn) {
        this.connection = conn;
    }
    
    public boolean saveUser(User user) {
        
        int rowsUpdated = 0;
        
        String query = "INSERT INTO \"Users\" VALUES (?, ?)";
        
        try {
            
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, user.getUsername().trim());
            statement.setString(2, user.getPassword().trim());
            rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean doesUserExist(User user) throws SQLException {
        
        ResultSet resultSet;
        
        String query = "SELECT * FROM \"Users\" WHERE \"username\" = ? AND \"password\" = ?";
        
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            resultSet = statement.executeQuery();
            
            if(resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public List<User> getAllUsers() throws SQLException {
        
        List<User> userList = new ArrayList();
        
        Statement statement = this.connection.createStatement();
        
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Users\"");
        
        while(resultSet.next()){
            User u = new User(resultSet.getString(1), resultSet.getString(2));
            userList.add(u);
        }
        
        return userList;
    }
}
