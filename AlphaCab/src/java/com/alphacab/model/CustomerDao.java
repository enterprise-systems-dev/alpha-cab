/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paul, Trym
 */
public class CustomerDao extends UserDao {

    @Override
    public boolean saveSpecific(User user) {
        
        String customersQuery = "INSERT INTO Customer (address, name, userid) VALUES (?, ?, ?)";
        
        Customer customer = (Customer) user;
        
        int rowsUpdated = 0;
        
        try {
            PreparedStatement customersStatement = getConnection().prepareStatement(customersQuery);
            customersStatement.setString(1, customer.getAddress());
            customersStatement.setString(2, customer.getName());
            customersStatement.setInt(3, customer.getId());
            
            rowsUpdated = customersStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rowsUpdated > 0;
    }

    @Override
    public User findSpecific(int userID, String uname, String pword, String role){
        
        String query = "SELECT * FROM Customer WHERE userid = ?";
        
        ResultSet resultSet;
        
        Customer customer = null;
        
        String name;
        
        String address;
        
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setInt(1, userID);
            
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                
                name = resultSet.getString("name");
                address = resultSet.getString("address");
                customer = new Customer(uname, pword, role, name, address);
                customer.setId(userID);
            }
            
        }  catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return customer;
    }
    
    @Override
    public boolean deleteSpecific(User user) {
                
        String query = "DELETE FROM Customer WHERE userid = ?";
        
        Customer customer = (Customer) user;
        
        int updateCount = 0;
        
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setInt(1, customer.getId());
            
            updateCount = statement.executeUpdate();
            
        }  catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return updateCount > 0;
    }
}
