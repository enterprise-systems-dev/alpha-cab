/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.controller;

import com.alphacab.model.User;
import com.alphacab.model.UserDao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Paul
 */
public class LoginServlet extends HttpServlet {

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
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/login.jsp");
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

        String addUserButton = request.getParameter("add-user-button");
        
        // if Add User button was clicked, redirect to AddUser page
        if(addUserButton != null) {
            String addUserURI = request.getContextPath() + "/AddUser";
            response.sendRedirect(addUserURI);          
        }
        
        UserDao userDao = new UserDao();
        
        userDao.connect((Connection)request.getServletContext().getAttribute("connection"));

        String username = request.getParameter("username-textbox");
        
        String password = request.getParameter("password-textbox");
        
        User u = new User(username, password);
        
        boolean userExists = false;
        
        try {
            userExists = userDao.doesUserExist(u);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(userExists) {           
            request.setAttribute("user", u);
            
            // create user session and cookie
            HttpSession session = request.getSession();
            session.setAttribute("user", u.getUsername());
            session.setMaxInactiveInterval(20 * 60);
            Cookie cookie = new Cookie("user", u.getUsername());
            cookie.setMaxAge(20 * 60);
            response.addCookie(cookie);
            
            // after creating session, redirect to home page
            response.sendRedirect("index.jsp");
        } else {
            // if user does not exist, forward error message back to login page
            request.setAttribute("error", "Login Unsuccessful");
            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/login.jsp");
            view.forward(request, response);
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
