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
        <table style="width:25%">
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Registration</th>
            </tr>
        <%
            ArrayList<Customer> customerList = (ArrayList)request.getAttribute("customerList");
            for(Customer customer : customerList) {
                out.print("<tr>");
                out.print("<td>" + customer.getId() + "</td>");
                out.print("<td>" + customer.getUsername()+ "</td>");  //getName
                out.print("<td>" + customer.getPassword()+ "</td>");  //getRegistration
                out.print("</tr>");
            }
        %>
        </form>
    </body>
</html>
