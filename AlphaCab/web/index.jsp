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
      <%  User sessionUser = (User)session.getAttribute("user");
            out.print(String.format("Hello %s, your user type is %s and id is %d", 
                sessionUser.getUsername(), sessionUser.getRole(), sessionUser.getId()));%><br><br>
                
                <%
                    if (sessionUser.getRole().equalsIgnoreCase("admin")) {
                 %>
                <a href="AddUser">Add User</a>
                <%}%>
    </body>
</html>
