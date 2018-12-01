<%-- 
    Document   : customers
    Created on : 01-Dec-2018, 11:59:15
    Author     : marcus
--%>

<%@page import="com.alphacab.model.Customer"%>
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
        <h1>Welcome to the Customers page</h1>
        Customer List<br>
        <form method="post">
        <table style="width:100%">
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Address</th>
            </tr>
        <%
            ArrayList<Customer> customerList = (ArrayList)request.getAttribute("customerList");
            for(Customer customer : customerList) {
                out.print("<tr>");
                out.print("<td>" + customer.getId() + "</td>");
                out.print("<td>" + customer.getName() + "</td>");
                out.print("<td>" + customer.getAddress()+ "</td>");
                out.print("</tr>");
            }
        %>
        </form>
    </body>
</html>

