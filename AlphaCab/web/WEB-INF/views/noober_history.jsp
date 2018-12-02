<%-- 
    Document   : noober_history
    Created on : 01-Dec-2018, 16:16:29
    Author     : marcus
--%>

<%@page import="com.alphacab.model.User"%>
<%@page import="com.alphacab.model.Demand"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.alphacab.model.Driver"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/header.jsp"/>
        <h1>Showing Noober history for:</h1>
        <%
            User sessionUser = (User) session.getAttribute("user");
            out.print(String.format("%s, your user type is %s and id is %d",
                    sessionUser.getUsername(), sessionUser.getRole(), sessionUser.getId()));%><br><br>
        <form method="post">
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
            ArrayList<Demand> demandList = (ArrayList)request.getAttribute("demandList");
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
        </form>
    </body>
</html>