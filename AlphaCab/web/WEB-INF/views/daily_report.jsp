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
        <jsp:include page="/resources/css/cdn_css.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Daily Report</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h1>Daily Report by Date</h1>
        <form method="post" action="DailyReport">
        <input type="text" name="date-textbox" placeholder="yyyy-mm-dd"><br><br>
        <input type="submit" name="get-bookings-button" value="Get Bookings"><br>
        <%
            if (request.getAttribute("error") != null) {
                out.print(request.getAttribute("error"));
            }
        %>
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
                
                String stringDate = new SimpleDateFormat("yyyy-MM-dd").format((Date)request.getAttribute("date"));
                
                if(journeyList != null && journeyList.size() > 0) { // prevents table headers being displayed if there are no results
                    
                out.print("<table style=\"width:100%\">");
                out.print("<tr>");
                out.print("<th>Customer Name</th>");
                out.print("<th>Driver</th>");
                out.print("<th>Address</th>");
                out.print("<th>Destination</th>");
                out.print("<th>Distance</th>");
                out.print("<th>Cost</th>");
                out.print("</tr>");
                    
                float total = 0;
                
                for(Journey journey : journeyList) {
                    out.print("<tr>");
                    out.print("<td>" + journey.getName() + "</td>");
                    out.print("<td>" + journey.getDriver().getRegistration() + "</td>");
                    out.print("<td>" + journey.getAddress() + "</td>");
                    out.print("<td>" + journey.getDestination() + "</td>");
                    out.print("<td>" + journey.getDistance() + "</td>");
                    out.print(String.format("<td>£%.2f</td>", journey.getCost()));
                    out.print("</tr>");
                    
                    total += journey.getCost();
                }
                
                out.print("</table>");
                // print number of journeys and total turnover below journey table
                out.print("<br>Number of journeys served: " + journeyList.size());
                out.print("<br>Total Turnover: £" + total);
                } else {
                    out.print("<br>No journeys found for " + stringDate);
                }
            %>
            </form>
        <jsp:include page="/resources/js/cdn_js.jsp"/>
    </body>
</html>
