/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.util;

import com.alphacab.model.UserDao;
import java.sql.Connection;

/**
 *
 * @author Paul
 */
public class JourneyCostCalculator {

    private final Connection conn;
    
    public JourneyCostCalculator(Connection conn) {
        this.conn = conn;
    }
    
    public double calculateCostExVAT(double distance) {
        double cost = 0;
        
        UserDao userDao = new UserDao();
        
        userDao.connect(conn);
        
        double[] rates = userDao.getBaseRateAndRatePerMile();
        
        double baseRate = rates[0];
        
        double ratePerMile = rates[1];
        
        double distanceCost = distance * ratePerMile;


        cost += baseRate;

        cost += distanceCost;


        
        return cost;
    }
    
    public double calculateCostIncVAT(double distance) {
        double cost = 0;
        
        UserDao userDao = new UserDao();
        
        userDao.connect(conn);
        
        double[] rates = userDao.getBaseRateAndRatePerMile();
        
        double baseRate = rates[0];
        
        double ratePerMile = rates[1];
        
        double distanceCost = distance * ratePerMile;


        cost += baseRate;

        cost += distanceCost;
        
        cost *= 1.2;
        
        return cost;
    }
}
