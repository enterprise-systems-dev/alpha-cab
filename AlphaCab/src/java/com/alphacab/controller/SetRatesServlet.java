/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.controller;

import com.alphacab.model.UserDao;
import java.io.IOException;
import static java.lang.System.out;
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
public class SetRatesServlet extends HttpServlet {

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

        UserDao userDao = new UserDao();

        userDao.connect((Connection) request.getServletContext().getAttribute("connection"));

        double[] rates;

        rates = userDao.getBaseRateAndRatePerMile();

        if (rates != null) {
            request.setAttribute("base-rate", rates[0]);
            request.setAttribute("rate-per-mile", rates[1]);
        }

        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/set_rates.jsp");
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
        
        // Get values from jsp and update database
        UserDao userDao = new UserDao();
        
        userDao.connect((Connection) request.getServletContext().getAttribute("connection"));
        
        String newBase;
        String newPerMile;
        String message = "";
        
        try {
            newBase = (String) request.getAttribute("base-rate-textbox");
            newPerMile = (String) request.getAttribute("per-mile-textbox");
            
            if (newBase != null) {
                userDao.setBaseRate(Double.parseDouble(newBase));
                message += "\nBase rate has been updated!";
            }
            
            if (newPerMile != null) {
                userDao.setBaseRate(Double.parseDouble(newPerMile));
                message += "\nRate per mile has been updated!";
            }
            
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println(e);
        }
        
        request.setAttribute("message", message);
        
        doGet(request, response);
        
//        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/set_rates.jsp");
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
