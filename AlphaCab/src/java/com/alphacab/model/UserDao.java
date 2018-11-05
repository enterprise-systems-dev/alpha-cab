/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    
    public boolean doesUserExist(User user) {
        
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
