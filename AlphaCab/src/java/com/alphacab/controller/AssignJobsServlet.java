/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.controller;

import com.alphacab.model.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marcus
 */
public class AssignJobsServlet extends HttpServlet {

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
        
        // Display outstanding demands and available drivers
        
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/assign_jobs.jsp");
        
        UserDao userDao = new UserDao();
        
        userDao.connect((Connection)request.getServletContext().getAttribute("connection"));
        
        request.setAttribute("outstandingDemands", userDao.getOutstandingDemands());
        
        request.setAttribute("availableDrivers", userDao.getAvailableDrivers());
                        
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
        
        // Give selected driver the selected demand
        
//        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/assign_jobs.jsp");
        int driverID = 0;
        int jobID = 0;
                
        try {
            driverID = Integer.parseInt(request.getParameter("driverID"));
            jobID = Integer.parseInt(request.getParameter("jobID"));
            
        } catch (NumberFormatException e) {
            System.out.println("Input has to ba a digit - NumberFormatException:" + e);
            
            request.setAttribute("error", "Input has to be an integer");
            
            // Bad practice, find better way to do this !!!!!!!!
            doGet(request, response);
//            view.forward(request, response);
            
        }
        
        UserDao userDao = new UserDao();
        
        userDao.connect((Connection)request.getServletContext().getAttribute("connection"));
        
        // if something goes wrong
        if (!userDao.assignJobToDriver(driverID, jobID)) {
            request.setAttribute("error", "Could not assign jod to driver");
        }
        
        request.setAttribute("message", "Driver " + driverID + " was assigned job " + jobID);
        
//        view.forward(request, response);        
        // Bad practice, find better way to do this !!!!!!!!
        doGet(request, response);
//        view.forward(request, response);
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
