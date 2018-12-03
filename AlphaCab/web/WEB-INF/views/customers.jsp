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
        <jsp:include page="/resources/css/cdn_css.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customers</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
        <h1 class="text-center">Customers</h1>
        <%  ArrayList<Customer> customerList = (ArrayList)request.getAttribute("customerList");
            out.print("<div class=\"text-center\">" + customerList.size() + " customers found </div><br>");
        %>
        <table class="table table-hover">
            <thead class="thead-light">
                <tr>
                  <th>Name</th>
                  <th>Address</th>
                </tr>
            </thead>
        <%
            
            for(Customer customer : customerList) {
                out.print("<tr>");
                out.print("<td>" + customer.getName() + "</td>");
                out.print("<td>" + customer.getAddress()+ "</td>");
                out.print("</tr>");
            }
        %>
        </table>
        </div>
    <jsp:include page="/resources/js/cdn_js.jsp"/>
    </body>
</html>

