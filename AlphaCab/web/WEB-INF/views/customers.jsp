<%-- 
    Document   : customer
    Created on : 24-Nov-2018, 12:34:13
    Author     : marcus
--%>

<%@page import="com.alphacab.model.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h1>Welcome to Customers page</h1>
        Customer List<br>
        <form method="post">
        <table style="width:50%">
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Registration</th>
            </tr>
            <%--        <%
            ArrayList<Driver> driverList = (ArrayList)request.getAttribute("driverList");
            for(Driver driver : driverList) {
                out.print("<tr>");
                out.print("<td>" + driver.getId() + "</td>");
                out.print("<td>" + driver.getUsername()+ "</td>");  //getName
                out.print("<td>" + driver.getPassword()+ "</td>");  //getRegistration
                out.print("</tr>");
            }
        %> --%>
        </form>
    </body>
</html>
