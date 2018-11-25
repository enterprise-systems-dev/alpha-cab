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
public class DriverDao extends UserDao {

    @Override
    public boolean saveSpecific(User user) {
        
        String driverQuery = "INSERT INTO Drivers (registration, name, userid) VALUES (?, ?, ?)";
        
        Driver driver = (Driver) user;
        
        int rowsUpdated = 0;
        
        try {
            PreparedStatement customersStatement = getConnection().prepareStatement(driverQuery);
            customersStatement.setString(1, driver.getRegistration());
            customersStatement.setString(2, driver.getName());
            customersStatement.setInt(3, driver.getId());
            
            rowsUpdated = customersStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rowsUpdated > 0;
    }
    
    @Override
    public User findSpecific(int userID, String uname, String pword, String role){
        
        String query = "SELECT * FROM Drivers WHERE userid = ?";
        
        ResultSet resultSet;
        
        Driver driver = null;
        
        String name;
        
        String registration;
        
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setInt(1, userID);
            
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                
                name = resultSet.getString("name");
                registration = resultSet.getString("registration");
                driver = new Driver(uname, pword, role, name, registration);
                driver.setId(userID);
            }
            
        }  catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return driver;
    }
    
    @Override
    public boolean deleteSpecific(User user) {
        
        String query = "DELETE FROM Drivers WHERE userid = ?";
        
        Driver driver = (Driver) user;
        
        int updateCount = 0;
        
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setInt(1, driver.getId());
            
            updateCount = statement.executeUpdate();
            
        }  catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return updateCount > 0;
    }
}
