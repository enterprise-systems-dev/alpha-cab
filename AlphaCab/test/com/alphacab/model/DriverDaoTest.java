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
public class DriverDaoTest {
    
    public DriverDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of connect method, of class DriverDao.
     */
    @Test
    public void testConnect() {
        System.out.println("connect");
        Connection conn = null;
        DriverDao instance = new DriverDao();
        instance.connect(conn);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveDriver method, of class DriverDao.
     */
    @Test
    public void testSaveDriver() {
        System.out.println("saveDriver");
        Driver driver = null;
        DriverDao instance = new DriverDao();
        boolean expResult = false;
        boolean result = instance.saveDriver(driver);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteDriver method, of class DriverDao.
     */
    @Test
    public void testDeleteDriver() {
        System.out.println("deleteDriver");
        int id = 0;
        DriverDao instance = new DriverDao();
        boolean expResult = false;
        boolean result = instance.deleteDriver(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllDrivers method, of class DriverDao.
     */
    @Test
    public void testGetAllDrivers() throws Exception {
        System.out.println("getAllDrivers");
        DriverDao instance = new DriverDao();
        List<Driver> expResult = null;
        List<Driver> result = instance.getAllDrivers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
