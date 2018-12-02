<%-- 
    Document   : daily_report.jsp
    Created on : 02-Dec-2018, 11:31:08
    Author     : Paul
--%>

<%@page import="com.alphacab.model.Journey"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Daily Report</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h1>Daily Report for 
            <%
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                out.print(sdf.format(date));
            %>
        </h1>
        <table>
        <tr>
          <th>Customer Name</th>
          <th>Driver</th>
          <th>Address</th>
          <th>Destination</th>
          <th>Distance</th>
          <th>Cost</th>
        </tr>
            <% 
                // servlet returns list of journeys
                ArrayList<Journey> journeyList = (ArrayList)request.getAttribute("journeyList");
                
                float total = 0;
                
                for(Journey journey : journeyList) {
                    out.print("<tr>");
                    out.print("<td>" + journey.getName() + "</td>");
                    out.print("<td>" + journey.getRegistration() + "</td>");
                    out.print("<td>" + journey.getAddress() + "</td>");
                    out.print("<td>" + journey.getDestination() + "</td>");
                    out.print("<td>" + journey.getDistance() + "</td>");
                    out.print("<td>" + journey.getCost() + "</td>");
                    out.print("</tr>");
                    
                    total += journey.getCost();
                }
                
                // print number of journeys and total turnover below journey table
                out.print("Number of journeys served: " + journeyList.size());
                out.print("Total Turnover: " + total);
            %>
    </body>
</html>
