/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.controller;

import com.alphacab.model.Admin;
import com.alphacab.model.AdminDao;
import com.alphacab.model.Customer;
import com.alphacab.model.CustomerDao;
import com.alphacab.model.Driver;
import com.alphacab.model.DriverDao;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Paul
 */
public class AddUserServlet extends HttpServlet {

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
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/add_user.jsp");
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

        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/add_user.jsp");

        Connection c = (Connection) request.getServletContext().getAttribute("connection");
        
        String username = request.getParameter("username-textbox");

        String password = request.getParameter("password-textbox");

        String selectedRole = request.getParameter("role");

        AdminDao adminDao = null;
        Admin newAdmin = null;
        
        CustomerDao customerDao = null;
        Customer newCustomer = null;
        
        DriverDao driverDao = null;
        Driver newDriver = null;
        
        boolean savedSuccesfuly = false;
        
        String successMsg = " user added";
        
        String errorMsg = "Error adding ";

        switch (selectedRole) {

            case ("admin"):
                
                adminDao = new AdminDao();

                newAdmin = new Admin(username, password, selectedRole);
                
                successMsg = selectedRole + successMsg;
                
                errorMsg = errorMsg + selectedRole;

                break;

            case ("customer"):

                String cName = request.getParameter("customer-name-textbox");

                String address = request.getParameter("address-textbox");
                
                customerDao = new CustomerDao();

                newCustomer = new Customer(username, password, selectedRole, cName, address);

                successMsg = selectedRole + successMsg;
                
                errorMsg = errorMsg + selectedRole;

                break;

            case ("driver"):

                String dName = request.getParameter("driver-name-textbox");

                String registration = request.getParameter("registration-textbox");
                
                driverDao = new DriverDao();
                
                newDriver = new Driver(username, password, selectedRole, dName, registration);

                successMsg = selectedRole + successMsg;
                
                errorMsg = errorMsg + selectedRole;

                break;

            default:
                //Error
                break;
        }

        // Only one pair will be initialised
        if(adminDao != null && newAdmin != null) {
            adminDao.setConnection(c);
            savedSuccesfuly = adminDao.saveUser(newAdmin);
            
        } else if(customerDao != null && newCustomer != null) {
            customerDao.setConnection(c);
            savedSuccesfuly = customerDao.saveUser(newCustomer);
            
        } else if(driverDao != null && newDriver != null) { 
            driverDao.setConnection(c);
            savedSuccesfuly = driverDao.saveUser(newDriver);
            
        } else {
            // Error!!!
        }
        
        if(savedSuccesfuly) {
            request.setAttribute("message", successMsg);
        } else {
            request.setAttribute("error", errorMsg);
        }

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
    }

}
