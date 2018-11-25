/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.model;

/**
 *
 * @author Trym
 */
public class Admin extends User {

    public Admin(String username, String password, String role) {
        super(username, password, role);
    }
    
    public Admin(int id, String username, String password, String role) {
        super(id, username, password, role);
    }
}
