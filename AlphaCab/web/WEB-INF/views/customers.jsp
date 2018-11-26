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
        <form method="post" name="delete-customer-form" action="DeleteCustomer">
            <input type="text" name="selected-customer" placeholder="Customer`s ID"> 
            <input type="submit" name="delete-customer" value="Delete customer">
        </form>
        Customer List<br>
        <form method="post">
        <table style="width:25%">
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
                out.print("<td>" + customer.getName()+ "</td>");  //getName
                out.print("<td>" + customer.getAddress()+ "</td>");  //getAddress
                out.print("</tr>");
            }
        %>
        </form>
    </body>
</html>
