/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.model;

/**
 *
 * @author marcus
 */
public class Job {
    private String destintion;
    
    public Job() {
        destintion = "test";
    }

    public String getDestintion() {
        return destintion;
    }

    public void setDestintion(String destintion) {
        this.destintion = destintion;
    }
    
}
