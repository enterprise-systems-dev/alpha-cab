/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.model;

import java.sql.Connection;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcus
 */
public class UserDaoTest {
    
    public UserDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of connect method, of class UserDao.
     */
    @Test
    public void testConnect() {
        System.out.println("connect");
        Connection conn = null;
        UserDao instance = new UserDao();
        instance.connect(conn);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveUser method, of class UserDao.
     */
    @Test
    public void testSaveUser() {
        System.out.println("saveUser");
        User user = null;
        UserDao instance = new UserDao();
        boolean expResult = false;
        boolean result = instance.saveUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doesUserExist method, of class UserDao.
     */
    @Test
    public void testDoesUserExist() throws Exception {
        System.out.println("doesUserExist");
        User user = null;
        UserDao instance = new UserDao();
        boolean expResult = false;
        boolean result = instance.doesUserExist(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllUsers method, of class UserDao.
     */
    @Test
    public void testGetAllUsers() throws Exception {
        System.out.println("getAllUsers");
        UserDao instance = new UserDao();
        List<User> expResult = null;
        List<User> result = instance.getAllUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
