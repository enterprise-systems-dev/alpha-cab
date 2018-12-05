/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.controller;

import com.alphacab.model.Customer;
import com.alphacab.model.UserDao;
import java.io.IOException;
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
public class CallAlphaCabServlet extends HttpServlet {

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
        UserDao.connect((Connection) request.getServletContext().getAttribute("connection"));

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

        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/alphaCab_history.jsp");
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

        // Add demand = call AlphaCab
        String name = request.getParameter("name-textbox");
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

            customerDao.connect((Connection) request.getServletContext().getAttribute("connection"));

            demandAdded = customerDao.addDemand(logedInCustomer.getName(), startAddress, endAddress, pickupDate, pickupTime, logedInCustomer.getId());

            if (demandAdded) {
                request.setAttribute("message", "Your AlphaCab has been notified!");
                request.setAttribute("name", name);
                request.setAttribute("address", startAddress);
                request.setAttribute("destination", endAddress);
                request.setAttribute("date", pickupDate);
                request.setAttribute("time", pickupTime);

                double rates[] = customerDao.getBaseRateAndRatePerMile();
                
                double baseRate = rates[0];
                request.setAttribute("base-rate", rates[0]);
                
                double ratePerMile = rates[1];
                request.setAttribute("rate-per-mile", rates[1]);
                
                // calculate costs
                double distance = 4.0;
                request.setAttribute("distance", distance);
                
                double distanceCost = distance * ratePerMile;
                request.setAttribute("distanceCost", distanceCost);
                
                double totalExVAT = 0;
                
                totalExVAT += baseRate;
                
                totalExVAT += distanceCost;
                
                double totalIncVAT = (totalExVAT * 1.2);
                
                request.setAttribute("totalExVAT", totalExVAT);
                
                request.setAttribute("totalIncVAT", totalIncVAT);
                
                RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/invoice.jsp");
                view.forward(request, response);

            } else {
                System.out.println("Someting went wrong when adding demand !!!!!!!!!!!!!!!!!");
                request.setAttribute("error", "Could not call a AlphaCab.");
                RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                view.forward(request, response);
            }

        }


        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/invoice.jsp");
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
