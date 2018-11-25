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
public class CustomerDao extends UserDao {

    @Override
    public /*<T extends User>*/ boolean saveSpecific(User user) {
        
        String customersQuery = "INSERT INTO Customers (address, name, userid) VALUES (?, ?, ?)";
        
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
//
//    private Connection connection;
//
//    public CustomerDao() {
//    }
//    
//    public void connect(Connection conn) {
//        this.connection = conn;
//    }
//    
//    public <T extends User> boolean saveUserType(Class<T> type)  {
//        
//        String usersQuery = "INSERT INTO Users (username, password, role) VALUES (?, ?, ?)";
//        
//        String customersQuery = "INSERT INTO Customers (address, name, userid) VALUES (?, ?, ?)";
//        
//        try {
//            PreparedStatement usersStatement = this.connection.prepareStatement(usersQuery, Statement.RETURN_GENERATED_KEYS);
//            usersStatement.setString(1, customer.getUsername());
//            usersStatement.setString(2, customer.getPassword());
//            usersStatement.setString(3, customer.getRole()); //This could be done differently
//            
//            int rowsUpdated = usersStatement.executeUpdate();
//            
//            ResultSet userId = usersStatement.getGeneratedKeys();
//            
//            if(userId.next()) {
//                customer.setUserId(userId.getInt(1));
//            }
//            
//            PreparedStatement customersStatement = this.connection.prepareStatement(customersQuery);
//            customersStatement.setString(1, customer.getAddress());
//            customersStatement.setString(2, customer.getName());
//            customersStatement.setInt(3, customer.getUserId());
//            
//            rowsUpdated = customersStatement.executeUpdate();
//            
//            return true;
//        } catch(SQLException ex) {
//            
//        }
//        
//        return false;        
//    }
//    
//    //Remember to remove from the USER table as well as the CUSTOMER table
//    public boolean deleteCustomer(int id) {
//        
//        String deleteCustomerQuery = "DELETE FROM Customers WHERE id = ?";
//        
//        
//        try {
//            PreparedStatement customerStatement = this.connection.prepareStatement(deleteCustomerQuery);
//            
//            customerStatement.setInt(1, id);
//            
//            int rowsUpdated = customerStatement.executeUpdate();
//            
//            if(rowsUpdated > 0) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return false;
//    }
//    
//        public List<Customer> getAllCustomers() throws SQLException {
//        
//        List<Customer> customerList = new ArrayList();
//        
//        Statement statement = this.connection.createStatement();
//        
//        ResultSet resultSet = statement.executeQuery("SELECT id, name, registration FROM Customers");
//        
//        while(resultSet.next()){
//            customerList.add(new Customer(
//                    resultSet.getInt(1),    //ID
//                    "USRNAME",              //Username
//                    "PSSWORD",              //Password
//                    resultSet.getString(2), //Name
//                    resultSet.getString(3)) //Address
//            );
//        }
//        
//        return customerList;
//    }
}
