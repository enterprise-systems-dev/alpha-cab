<%-- 
    Document   : my_jobs
    Created on : 04-Dec-2018, 09:32:42
    Author     : Paul
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.alphacab.model.Journey"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/resources/css/cdn_css.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Jobs</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
        <h1 class="text-center">My Jobs</h1>
        <%  ArrayList<Journey> journeyList = (ArrayList)request.getAttribute("journeyList");
            out.print("<div class=\"text-center\">" + journeyList.size() + " jobs found </div><br>");
        %>
        <table class="table table-hover">
            <thead class="thead-light">
                <tr>
                  <th>Name</th>
                  <th>Address</th>
                  <th>Destination</th>
                  <th>Distance</th>
                  <th>Cost</th>
                  <th>Date</th>
                  <th>Time</th>
                </tr>
            </thead>
        <%
            for(Journey journey : journeyList) {
                out.print("<tr>");
                out.print("<td>" + journey.getName() + "</td>");
                out.print("<td>" + journey.getAddress() + "</td>");
                out.print("<td>" + journey.getDestination() + "</td>");
                out.print("<td>" + journey.getDistance() + "</td>");
                out.print("<td>" + journey.getCost() + "</td>");
                out.print("<td>" + journey.getDate() + "</td>");
                out.print("<td>" + journey.getTime() + "</td>");
                out.print("</tr>");
            }
        %>
        </table>
        </div>
        <jsp:include page="/resources/js/cdn_js.jsp"/>
    </body>
</html>
