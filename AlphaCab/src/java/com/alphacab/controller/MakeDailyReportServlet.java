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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marcus
 */
public class MakeDailyReportServlet extends HttpServlet {

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
        
                //create db access obj
        UserDao UserDao = new UserDao();
        
        UserDao.connect((Connection)request.getServletContext().getAttribute("connection"));
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");     

        String stringDate = sdf.format(Calendar.getInstance().getTime());
        Date date = new Date();
        request.setAttribute("journeyList", UserDao.getTodaysJourneys(date));
        request.setAttribute("date", date);
        
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/daily_report.jsp");    
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
        UserDao UserDao = new UserDao();
        
        UserDao.connect((Connection)request.getServletContext().getAttribute("connection"));
        
        String stringDate = request.getParameter("date-textbox");
        
        Date date = new Date();
        
        if(!stringDate.isEmpty()) {
            try {
                // needs checks to make sure date month and days entered are valid
                date = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
                
                // if date is in the future, set the date to today
                if(date.after(new Date())) {
                    date = new Date();
                }
            } catch (ParseException ex) {
                Logger.getLogger(MakeDailyReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
        request.setAttribute("journeyList", UserDao.getTodaysJourneys(date));
        request.setAttribute("date", date);
        
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/daily_report.jsp");
             
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
