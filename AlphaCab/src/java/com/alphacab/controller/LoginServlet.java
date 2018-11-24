/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.controller;

import com.alphacab.model.Admin;
//import com.alphacab.model.AdminDao;
import com.alphacab.model.Customer;
//import com.alphacab.model.CustomerDao;
import com.alphacab.model.Driver;
import com.alphacab.model.User;
import com.alphacab.model.UserDao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        
//        UserDao userDao = new UserDao();
        
        Connection c =   
        ((Connection)request.getServletContext().getAttribute("connection"));
        
        String username = request.getParameter("username-textbox");
        
        String password = request.getParameter("password-textbox");
        
        ResultSet resultSet;
        
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        
//        boolean userExists = false;
        
        User newUser = null;  //???
        
//        Admin u = new Admin();

        int newId;
        String newUname;
        String newPword;
        String newRole;
        
        try {
            //Query the database
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();  //???
            
            //Create new user with proper role
            if(resultSet.next()) {
                
                newId = resultSet.getInt("ID");
                newUname = resultSet.getString("USERNAME");
                newPword = resultSet.getString("PASSWORD");
                newRole = resultSet.getString("ROLE");
                
                switch(newRole) {
                case "admin":
                    newUser = new Admin(newId, newUname, newPword, newRole);
                    break;
                case "customer":
                    newUser = new Customer(newId, newUname, newPword, newRole);
                    break;
                case "driver":
                    newUser = new Driver(newId, newUname, newPword, newRole);
                    break;
                default:
                    //Error!!!
                }
                
                //If switch goes OK...
                if(newUser != null){
                    // create user session and cookie
                    HttpSession session = request.getSession();
                    session.setAttribute("user", newUser);
                    session.setMaxInactiveInterval(20 * 60);
                    Cookie cookie = new Cookie("user", newUser.getUsername());
                    cookie.setMaxAge(20 * 60);
                    response.addCookie(cookie);
                    
                    // after creating session, redirect to home page
                    response.sendRedirect("index.jsp");
                }
                
            } else {
                // if user does not exist, forward error message back to login page
                request.setAttribute("error", "Login Unsuccessful");
                RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/login.jsp");
                view.forward(request, response);
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
//            while(resultSet.next()){  // Debugging only!!!
//                String a1 = resultSet.getString(1);
//                String a2 = resultSet.getString(2);
//                String a3 = resultSet.getString(3);
//                String a4 = resultSet.getString("Role");
//                System.out.println(a1 + "   " + a2 + "   " + a3 + "   " + a4);
//            }
            
//            if(resultSet.next()) {
//                userExists = true;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
//        if(userExists) {  
//            
//            
//            // create user session and cookie
//            HttpSession session = request.getSession();
//            session.setAttribute("user", u);
//            session.setMaxInactiveInterval(20 * 60);
//            Cookie cookie = new Cookie("user", u.getUsername());
//            cookie.setMaxAge(20 * 60);
//            response.addCookie(cookie);
//            
//            // after creating session, redirect to home page
//            response.sendRedirect("index.jsp");
//        } else {
//            // if user does not exist, forward error message back to login page
//            request.setAttribute("error", "Login Unsuccessful");
//            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/login.jsp");
//            view.forward(request, response);
//        }
        

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
