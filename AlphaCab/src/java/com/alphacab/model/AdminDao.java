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
public class AdminDao extends UserDao {
    
    @Override
    public boolean saveSpecific(User user) {
        return true;  //There is no admin table
    }
//
//    private Connection connection;
//
//    public AdminDao() {
//    }
//    
//    public void connect(Connection conn) {
//        this.connection = conn;
//    }
//    
//    @Override
//    public <T extends User> boolean saveUserType(Class<T> admin) {
//        String adminsQuery = "INSERT INTO Admins (name, swagfactor, userid) VALUES (?, ?, ?)";
//        
//        try {
//            (Admin) admin;
//            PreparedStatement userStatement = this.connection.prepareStatement(adminsQuery);
//            ResultSet userId = userStatement.getGeneratedKeys();
//            
//            if(userId.next()) {
//                admin.setId(userId.getInt(1));
//            }
//            
//            userStatement.setString(1, admin.getName());
//            userStatement.setString(2, admin.getSwagFactor());
//            userStatement.setInt(3, admin.getUserId());
//            
//            rowsUpdated = adminsStatement.executeUpdate();
//            
//            return true;
//        } catch(SQLException ex) {
//            
//        }
//        
//        return false;
//    }
//    
//    
//    //Remember to remove from the USER table as well as the DRIVER table
//    public boolean deleteAdmin(int id) {
//        
//        String deleteDriverQuery = "DELETE FROM Admins WHERE id = ?";
//        
//        
//        try {
//            PreparedStatement adminStatement = this.connection.prepareStatement(deleteDriverQuery);
//            
//            adminStatement.setInt(1, id);
//            
//            int rowsUpdated = adminStatement.executeUpdate();
//            
//            if(rowsUpdated > 0) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return false;
//    }
//    
//        public List<Admin> getAllAdmins() throws SQLException {
//        
//        List<Admin> adminList = new ArrayList();
//        
//        Statement statement = this.connection.createStatement();
//        
//        ResultSet resultSet = statement.executeQuery("SELECT id, name, swagfactor FROM Admins");
//        
//        while(resultSet.next()){
//            adminList.add(new Admin(
//                    resultSet.getInt(1),    //ID
//                    "USRNAME",              //Username
//                    "PSSWORD",              //Password
//                    resultSet.getString(2), //Name
//                    resultSet.getString(3)) //swagfactor
//            );
//        }
//        
//        return adminList;
//    }
//
//    
//
//    
}
