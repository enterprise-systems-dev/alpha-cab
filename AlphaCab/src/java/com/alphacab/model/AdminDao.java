/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.model;

/**
 *
 * @author Paul, Trym
 */
public class AdminDao extends UserDao {
    
    @Override
    public boolean saveSpecific(User user) {
        return true;  //There is no admin table
    }
    
    @Override
    public User findSpecific(int userID, String uname, String pword, String role){   
        return null;  //Not necessary at the moment
    }
    
    @Override
    public boolean deleteSpecific(User user) {
        return true;  //Not necessary at the moment
    }
}
