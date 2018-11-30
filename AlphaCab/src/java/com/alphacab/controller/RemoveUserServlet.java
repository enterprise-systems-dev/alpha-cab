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
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Darren
 */
public class RemoveUserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
        
        System.out.print("0-------------------------------------------------------");
        
        //create db access obj
        UserDao userDao = new UserDao();
        userDao.connect((Connection)request.getServletContext().getAttribute("connection"));
        
        //add user list to request
        List<User> userList = userDao.getAllUsers();
        request.setAttribute("userList",userList);
        
        System.out.print("01------------------------------------------------------");
        
        //view - remove user
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/remove_user.jsp");
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
        
        //create db access obj
        UserDao userDao = new UserDao();
        userDao.connect((Connection)request.getServletContext().getAttribute("connection"));

        //get id of user to delete
        int userID = Integer.parseInt(request.getParameter("userID"));
        
        //remove user
        boolean successful = userDao.removeUser(userID);
        
        //add user list to request
        List<User> userList = userDao.getAllUsers();
        request.setAttribute("userList",userList);
        
        //set request params on success and forward to add_user.jsp
        if(successful == true)
            request.setAttribute("message", "user removed");
        else
            request.setAttribute("error", "Error removing user");
        
        //view - remove user
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/remove_user.jsp");
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
