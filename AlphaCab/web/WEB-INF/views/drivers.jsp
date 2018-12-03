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
        <jsp:include page="/resources/css/cdn_css.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Drivers Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h1>Welcome to the Drivers page</h1>
        Driver List<br>
        <form method="post">
        <table style="width:100%">
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
                out.print("<td>" + driver.getName() + "</td>");
                out.print("<td>" + driver.getRegistration() + "</td>");
                out.print("</tr>");
            }
        %>
        </form>
        <jsp:include page="/resources/js/cdn_js.jsp"/>
    </body>
</html>
