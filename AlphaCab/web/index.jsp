<%-- 
    Document   : index
    Created on : 05-Nov-2018, 13:13:42
    Author     : Paul
--%>

<%@page import="com.alphacab.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/header.jsp"/>
        <h1>WELCOME TO THE HOME PAGE!</h1>
        <%  User sessionUser = (User) session.getAttribute("user");
            out.print(String.format("Hello %s, your user type is %s and id is %d",
                    sessionUser.getUsername(), sessionUser.getRole(), sessionUser.getId()));%><br><br>

        <%
            if (sessionUser.getRole().equalsIgnoreCase("admin")) {
        %>
        <a href="AddUser">Add User</a><br>
        <a href="RemoveUser">Remove User</a><br>
        <a href="DailyReport">Daily Report</a><br>
        <a href="AssignJobs">Assign Jobs</a><br>
        <%}%>

        <%
            if (sessionUser.getRole().equalsIgnoreCase("customer")) {
        %>
        <form method="post" action="CallNoober">
            Where are you?
            <input type="text" name="address-textbox" placeholder="Your current address"><br>
            Where do you want to go?
            <input type="text" name="destination-textbox" placeholder="Your destination address"><br>
            When do you need the Noober?<br>
            <input type="text" name="pickup-date" placeholder="yyyy-mm-dd">
            <input type="text" name="pickup-time" placeholder="hh:mm:ss - 24h format">
            <%-- try this for date and time https://jqueryui.com/datepicker/ 
                 make sure date and time are in specified format 
                 thats how the database wants it.
            --%>
            <input type="submit" name="call-noober-button" value="Call Noober">
        </form>
            
            <form method="get" action="CallNoober">
                <input type="submit" name="view-noober-history-button" value="Noober History">
            </form>
        <%}%>
        <a href="Logout">Log out</a>
        
        <%
            if (request.getAttribute("error") != null) {
                out.print(request.getAttribute("error"));
            }
            if (request.getAttribute("message") != null) {
                out.print(request.getAttribute("message"));
            }
        %>
    </body>
</html>
