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
        <title>Drivers</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
        <h1 class="text-center">Drivers</h1>
        <%  ArrayList<Driver> driverList = (ArrayList)request.getAttribute("driverList");
            out.print("<div class=\"text-center\">" + driverList.size() + " drivers found </div><br>");
        %>
        <table class="table table-hover">
            <thead class="thead-light">
                <tr>
                  <th>Name</th>
                  <th>Registration</th>
                </tr>
            </thead>
        <%
            for(Driver driver : driverList) {
                out.print("<tr>");
                out.print("<td>" + driver.getName() + "</td>");
                out.print("<td>" + driver.getRegistration() + "</td>");
                out.print("</tr>");
            }
        %>
        </table>
        </div>
        <jsp:include page="/resources/js/cdn_js.jsp"/>
    </body>
</html>
