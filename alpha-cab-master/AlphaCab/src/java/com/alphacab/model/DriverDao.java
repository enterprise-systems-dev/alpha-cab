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
public class DriverDao extends UserDao{

    private Connection connection;

    public DriverDao() {
    }
    
    public void connect(Connection conn) {
        this.connection = conn;
    }
    
    @Override
    public <T extends User> boolean saveUserType(Class<T> type) {
        
        String driversQuery = "INSERT INTO Drivers (registration, name, userid) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement usersStatement = this.connection.prepareStatement(usersQuery, Statement.RETURN_GENERATED_KEYS);
            usersStatement.setString(1, user.getUsername());
            usersStatement.setString(2, driver.getPassword());
            usersStatement.setString(3, driver.getRole());
            
            int rowsUpdated = usersStatement.executeUpdate();
            
            ResultSet userId = usersStatement.getGeneratedKeys();
            
            if(userId.next()) {
                driver.setUserId(userId.getInt(1));
            }
            
            PreparedStatement driversStatement = this.connection.prepareStatement(driversQuery);
            driversStatement.setString(1, driver.getRegistration());
            driversStatement.setString(2, driver.getName());
            driversStatement.setInt(3, driver.getUserId());
            
            rowsUpdated = driversStatement.executeUpdate();
            
            return true;
        } catch(SQLException ex) {
            
        }
        
        return false;        
    }
    
    //Remember to remove from the USER table as well as the DRIVER table
    public boolean deleteDriver(int id) {
        
        String deleteDriverQuery = "DELETE FROM Drivers WHERE id = ?";
        
        
        try {
            PreparedStatement driverStatement = this.connection.prepareStatement(deleteDriverQuery);
            
            driverStatement.setInt(1, id);
            
            int rowsUpdated = driverStatement.executeUpdate();
            
            if(rowsUpdated > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DriverDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
        public List<Driver> getAllDrivers() throws SQLException {
        
        List<Driver> driverList = new ArrayList();
        
        Statement statement = this.connection.createStatement();
        
        ResultSet resultSet = statement.executeQuery("SELECT id, name, registration FROM Drivers");
        
        while(resultSet.next()){
            driverList.add(new Driver(
                    resultSet.getInt(1),    //ID
                    "USRNAME",              //Username
                    "PSSWORD",              //Password
                    resultSet.getString(2), //Name
                    resultSet.getString(3)) //Registration
            );
        }
        
        return driverList;
    }

}
