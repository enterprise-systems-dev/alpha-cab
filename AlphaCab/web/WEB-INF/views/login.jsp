<%-- 
    Document   : login
    Created on : 04-Nov-2018, 17:46:50
    Author     : Paul
--%>

<%@page import="java.util.List"%>
<%@page import="com.alphacab.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Welcome to Login Page</h1>
        <%
            out.print("hello<br>");
            out.print(request.getAttribute("message"));
            %>         
    </body>
</html>
