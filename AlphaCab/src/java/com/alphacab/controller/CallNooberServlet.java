/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.controller;

import com.alphacab.model.Customer;
import com.alphacab.model.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marcus
 */
public class CallNooberServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //create db access obj
        UserDao UserDao = new UserDao();
        UserDao.connect((Connection)request.getServletContext().getAttribute("connection"));
        
        // Get the current session wthout creating one it it dosent exist
        HttpSession currentSession = request.getSession(false);
        
        Customer logedInCustomer;
        
        // Check if there is a loged in user
        if (currentSession == null) {
            System.out.println("No session found !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        } else {
            
            // What if user is not a customer, will that ever happen???
            logedInCustomer = (Customer) currentSession.getAttribute("user");
            
            //add demandList to request
            request.setAttribute("demandList", UserDao.getDemandsForCustomer(logedInCustomer));
            
        }
        
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/noober_history.jsp");
        view.forward(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Add demand = call Noober
        
        String startAddress = request.getParameter("address-textbox");
        String endAddress = request.getParameter("destination-textbox");
        String pickupDate = request.getParameter("pickup-date");
        String pickupTime = request.getParameter("pickup-time");
        
        UserDao customerDao = new UserDao();
        
        Customer logedInCustomer;
        
        boolean demandAdded = false;
        
        // Get the current session wthout creating one it it dosent exist
        HttpSession currentSession = request.getSession(false);
        
        // Check if there is a loged in user
        if (currentSession == null) {
            System.out.println("No session found !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        } else {
            
            // What if user is not a customer, will that ever happen???
            logedInCustomer = (Customer) currentSession.getAttribute("user");
                    
            customerDao.connect((Connection)request.getServletContext().getAttribute("connection"));
        
            demandAdded = customerDao.addDemand(logedInCustomer.getName(), startAddress, endAddress, pickupDate, pickupTime, logedInCustomer.getId());
            
            if (demandAdded) {
                request.setAttribute("message", "Your Noober has been notified!");
                
            } else {
                System.out.println("Someting went wrong when adding demand !!!!!!!!!!!!!!!!!");
                request.setAttribute("error", "Could not call a Noober.");
            }
            
        }
        
        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
