/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.model;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author marcus
 */
public class DriverTest {
    
    public DriverTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getName method, of class Driver.
     */
    @Test
    public void testGetName() {
        System.out.print("getName");
        Driver instance = new Driver("user", "pass", "role", "name", "reg");
        String expResult = "name";
        String result = instance.getName();
        assertEquals(expResult, result);
        System.out.print(" - OK\n");
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Driver.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Driver instance = new Driver();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Driver.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Driver instance = new Driver();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Driver.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Driver instance = new Driver();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRegistration method, of class Driver.
     */
    @Test
    public void testGetRegistration() {
        System.out.println("getRegistration");
        Driver instance = new Driver();
        String expResult = "";
        String result = instance.getRegistration();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRegistration method, of class Driver.
     */
    @Test
    public void testSetRegistration() {
        System.out.println("setRegistration");
        String registration = "";
        Driver instance = new Driver();
        instance.setRegistration(registration);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserId method, of class Driver.
     */
    @Test
    public void testGetUserId() {
        System.out.println("getUserId");
        Driver instance = new Driver();
        int expResult = 0;
        int result = instance.getUserId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUserId method, of class Driver.
     */
    @Test
    public void testSetUserId() {
        System.out.println("setUserId");
        int userId = 0;
        Driver instance = new Driver();
        instance.setUserId(userId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
