/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.controller;

import com.alphacab.model.Admin;
import com.alphacab.model.Driver;
import com.alphacab.model.Customer;
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
        
        //PLAYER 3 (stamper) HAS ENTERED THE GAME B****
        // ...caps lock comments are real dramatic btw...
        
        /*
        *
        *   NEEDS DOING:
        *   TRANSITION THE ROLES INTO THE SWITCH RATHER THAN IF/ELSE
        *   MAKE SURE THE USER AND ADMIN WORKS WITH THE NEW CLASS LAYOUT
        *   IMPLEMENT CUSTOMER AND DRIVER CODE FOR THE NEW CLASSES
        *
        */
        
        //get general user fields
        String user = request.getParameter("username-textbox");
        String pass = request.getParameter("password-textbox");
        
        String role = request.getParameter("role").toLowerCase();
        
        //create db access obj
        UserDao userDao = new UserDao();
        userDao.connect((Connection)request.getServletContext().getAttribute("connection"));
        
        //flag for checking whether user was added to db successfully
        boolean successful = false;
        
        //get user type, specific fields, and add to db
        switch(role){
            case("admin"):
                Admin a = new Admin(user, pass);
                
                successful = userDao.saveUser(a);
                break;
            case("driver"):
                Driver d = new Driver(user, pass);
                
                d.setName(request.getParameter("driver-name-textbox"));
                d.setRegistration(request.getParameter("registration-textbox"));
                
                successful = userDao.saveUser(d);
                break;
            case("customer"):
                Customer c = new Customer(user, pass);
                
                c.setName(request.getParameter("customer-name-textbox"));
                c.setAddress(request.getParameter("address-textbox"));
                
                successful = userDao.saveUser(c);
                break;
        }
        
        //set request params on success and forward to add_user.jsp
        if(successful == true)
            request.setAttribute("message", "user added");
        else
            request.setAttribute("error", "Error adding admin user");
        
        //view - add user
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/add_user.jsp");
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
