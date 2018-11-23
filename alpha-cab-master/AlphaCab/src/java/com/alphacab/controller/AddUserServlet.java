/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.controller;

import com.alphacab.model.Driver;
import com.alphacab.model.DriverDao;
import com.alphacab.model.User;
import com.alphacab.model.UserDao;
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

    //  Click on the + sign on the left to edit the code.">
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
//        if(request.getParameter("add-user-button") == null) {
//            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/add_user.jsp");
//            view.forward(request, response);
//        } else if(request.getParameter("role").equalsIgnoreCase("driver")){      
//            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/drivers/add_driver.jsp");
//            view.forward(request, response);
//        } else if(request.getParameter("role").equalsIgnoreCase("customer")) {
//            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/customers/add_customer.jsp");
//            view.forward(request, response);
//        } else if(request.getParameter("role").equalsIgnoreCase("admin")) {
//            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/add_admin.jsp");
//            view.forward(request, response);
//        } else {
//            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/add_user.jsp");
//            view.forward(request, response);
//        }
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
        
        String selectedRole = request.getParameter("role");
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/add_user.jsp");
        String username = request.getParameter("username-textbox");
        String password = request.getParameter("password-textbox");
        
        /*
        *
        *   NEEDS DOING:
        *   TRANSITION THE ROLES INTO THE SWITCH RATHER THAN IF/ELSE
        *   MAKE SURE THE USER AND ADMIN WORKS WITH THE NEW CLASS LAYOUT
        *   IMPLEMENT CUSTOMER AND DRIVER CODE FOR THE NEW CLASSES
        *
        */
        
        
        switch(selectedRole.toLowerCase()){
            
                case("admin"):
                    UserDao userDao = new UserDao();
            
                    userDao.connect((Connection)request.getServletContext().getAttribute("connection"));
            
                    User user = new User(username, password, selectedRole);
            
                    if(userDao.saveUser(user)) {
                        request.setAttribute("message", "admin user added");
                    } else {
                        request.setAttribute("error", "Error adding admin user");
                    }     
                    view.forward(request, response);
            
                    break;
                case("customer"):
                    break;
                case("driver"):
                    // add driver
                    DriverDao driverDao = new DriverDao();
        
                    driverDao.connect((Connection)request.getServletContext().getAttribute("connection"));

                    String role = "driver";

                    String name = request.getParameter("driver-name-textbox");

                    String registration = request.getParameter("registration-textbox");

                    Driver driver = new Driver(username, password, role, name, registration);
        
                    if(driverDao.saveDriver(driver)) {
                        request.setAttribute("message", "Driver added");
                    } else {
                        request.setAttribute("error", "Error adding driver");
                    }     
                    
                    view.forward(request, response);
                    break;
                default:
                    //Error
                    break;
        }
        if(selectedRole.equalsIgnoreCase("admin")) {
        } else if(selectedRole.equalsIgnoreCase("customer")) {
            // add customer
            
        } else if(selectedRole.equalsIgnoreCase("driver")) {
            
            
        } else {
            //error
        }
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
