<%-- 
    Document   : customers
    Created on : 16-Nov-2018, 08:47:13
    Author     : Paul
--%>

<%@page import="com.alphacab.model.Driver"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Drivers Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h1>Welcome to the Drivers page</h1>
        <form method="post" name="delete-user-form" action="Drivers">
            <input type="text" name="selected-driver"> 
            <input type="submit" name="delete-driver" value="Driver">
        </form>
        Driver List<br>
        <form method="post">
        <table style="width:25%">
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Registration</th>
            </tr>
        <%
            ArrayList<Driver> driverList = (ArrayList)request.getAttribute("driverList");
            for(Driver driver : driverList) {
                out.print("<tr>");
                out.print("<td>" + driver.getId() + "</td>");
                out.print("<td>" + driver.getUsername()+ "</td>");  //getName
                out.print("<td>" + driver.getPassword()+ "</td>");  //getRegistration
                out.print("</tr>");
            }
        %>
        </form>
    </body>
</html>
