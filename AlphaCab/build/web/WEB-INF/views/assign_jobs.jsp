<%-- 
    Document   : assign_jobs
    Created on : 02-Dec-2018, 15:03:32
    Author     : marcus
--%>

<%@page import="com.alphacab.model.Driver"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.alphacab.model.Demand"%>
<%@page import="com.alphacab.model.Demand"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/resources/css/cdn_css.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assign Jobs</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h3>Select Driver and Customer</h3>
        <form method="post" name="give-jobs-form" action="AssignJobs">
            Enter drivers ID: <input type="text" name="driverID" placeholder="Drivers ID"><br>
            Enter job ID: <input type="text" name="jobID" placeholder="Job ID"><br>
            <input type="submit" name="assign-driver-button" value="Assign Driver">
        </form>
        <%
            if (request.getAttribute("error") != null) {
                out.print(request.getAttribute("error"));
            }
            if (request.getAttribute("message") != null) {
                out.print(request.getAttribute("message"));
            }
        %>
        <h1>Available drivers</h1>
        <table style="width:100%">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Registration</th>
                <th>Status</th>
            </tr>
        <%
            ArrayList<Driver> driverList = (ArrayList)request.getAttribute("availableDrivers");
            for(Driver driver : driverList) {
                out.print("<tr>");
                out.print("<td>" + driver.getId() + "</td>");
                out.print("<td>" + driver.getName() + "</td>");
                out.print("<td>" + driver.getRegistration() + "</td>");
                out.print("<td>" + driver.isBusy() + "</td>");
                out.print("</tr>");
            }
        %>
        </table>
        <h1>Pending jobs</h1>
        <table style="width:100%">
            <tr>
                <th>id</th>
                <th>name</th>
                <th>address</th>
                <th>destination</th>
                <th>date</th>
                <th>time</th>
                <th>status</th>
                <th>customerid</th>
            </tr>
        <%
            ArrayList<Demand> demandList = (ArrayList)request.getAttribute("outstandingDemands");
            for(Demand demand : demandList) {
                out.print("<tr>");
                out.print("<td>" + demand.getId() + "</td>");
                out.print("<td>" + demand.getName() + "</td>");
                out.print("<td>" + demand.getAddress()+ "</td>");
                out.print("<td>" + demand.getDestination()+ "</td>");
                out.print("<td>" + demand.getDate()+ "</td>");
                out.print("<td>" + demand.getTime()+ "</td>");
                out.print("<td>" + demand.getStatus()+ "</td>");
                out.print("<td>" + demand.getCustomerid()+ "</td>");
                out.print("</tr>");
            }
        %>
        </table>
        <jsp:include page="/resources/js/cdn_js.jsp"/>
    </body>
</html>
