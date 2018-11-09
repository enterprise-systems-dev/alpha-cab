<%-- 
    Document   : index
    Created on : 05-Nov-2018, 13:13:42
    Author     : Paul
--%>

<%@page import="java.util.List"%>
<%@page import="com.alphacab.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form method="post" action="Login">
            Username:<br>
            <input type="text" name="username-textbox"><br>
            Password:<br>
            <input type="password" name="password-textbox"><br><br>
            <input name="login-button" type="submit" value="Login"/>
            <input name="register-button" type="submit" formmethod="get" value="Register"/>
        </form>
    </body>
</html>
