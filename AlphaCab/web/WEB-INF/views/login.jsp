<%-- 
    Document   : login
    Created on : 04-Nov-2018, 17:46:50
    Author     : Paul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="resources/scripts/index.js"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Welcome to Login Page!</h1>
        <form method="post" name="login-form" action="Login">
            <input type="text" name="username-textbox" placeholder="Username" required pattern="[^\s]+" 
                   oninvalid="this.setCustomValidity('No whitespace allowed')" oninput="this.setCustomValidity('')"><br><br>
            <input type="password" name="password-textbox" placeholder="Password" required pattern="[^\s]+" 
                   oninvalid="this.setCustomValidity('No whitespace allowed')" oninput="this.setCustomValidity('')"><br><br>
            <input name="login-button" type="submit" value="Login"/><br><br>
            <%
                if(request.getAttribute("error") != null) {
                    out.print(request.getAttribute("error"));
                }
            %>
        </form>
        <br>
        Don`t want to register but still need a Noober?
        <form method="post" name="guest-login-form" action="Login">
            <input type="hidden" name="guest-form" value="guest-login-form">
            <input type="submit" name="guest-login" value="Get an emergency Noober"/>
        </form>
    </body>
</html>
