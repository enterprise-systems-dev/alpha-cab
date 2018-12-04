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
        <div class="container">
            <h1 class="text-center display-4">Assign Job</h3>
            <%
                if (request.getAttribute("error") != null) {
                    out.print(request.getAttribute("error"));
                }
                if (request.getAttribute("message") != null) {
                    out.print(request.getAttribute("message"));
                }
            %>
            <form method="post" name="give-jobs-form" action="AssignJobs">
                <div class="form-group">
                    <label for="driverID">Driver ID</label>
                    <input type="text" class="form-control" name="driverID" id="driverID" placeholder="Enter Driver ID">
                </div>
                <div class="form-group">
                    <label for="jobID">Job ID</label>
                    <input type="text" class="form-control" name="jobID" id="jobID" placeholder="Enter Job ID">
                </div>
                <button class="btn btn-primary" type="submit" name="assign-driver-button" value="Assign Driver">Assign Driver</button>
            </form><br>
            <div class="row">
                <h3 class="text-center">Available drivers</h3>
                <table class="table table-hover">
                    <tr>
                        <th>Driver ID</th>
                        <th>Name</th>
                        <th>Registration</th>
                        <th>Status</th>
                    </tr>
                    <%
                        ArrayList<Driver> driverList = (ArrayList) request.getAttribute("availableDrivers");
                        for (Driver driver : driverList) {
                            out.print("<tr>");
                            out.print("<td>" + driver.getId() + "</td>");
                            out.print("<td>" + driver.getName() + "</td>");
                            out.print("<td>" + driver.getRegistration() + "</td>");
                            out.print("<td>" + driver.isBusy() + "</td>");
                            out.print("</tr>");
                        }
                    %>
                </table>
                <h3 class="text-center">Pending jobs</h3>
                <table class="table table-hover">
                    <tr>
                        <th>Job ID</th>
                        <th>Customer Name</th>
                        <th>Address</th>
                        <th>Destination</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Status</th>
                        <th>Customer ID</th>
                    </tr>
                    <%
                        ArrayList<Demand> demandList = (ArrayList) request.getAttribute("outstandingDemands");
                        for (Demand demand : demandList) {
                            out.print("<tr>");
                            out.print("<td>" + demand.getId() + "</td>");
                            out.print("<td>" + demand.getName() + "</td>");
                            out.print("<td>" + demand.getAddress() + "</td>");
                            out.print("<td>" + demand.getDestination() + "</td>");
                            out.print("<td>" + demand.getDate() + "</td>");
                            out.print("<td>" + demand.getTime() + "</td>");
                            out.print("<td>" + demand.getStatus() + "</td>");
                            out.print("<td>" + demand.getCustomerid() + "</td>");
                            out.print("</tr>");
                        }
                    %>
                </table>
            </div>
        </div>
        <jsp:include page="/resources/js/cdn_js.jsp"/>
    </body>
</html>
