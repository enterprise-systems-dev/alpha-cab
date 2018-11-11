<%-- 
    Document   : register.jsp
    Created on : 05-Nov-2018, 15:35:32
    Author     : Paul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add User</title>
    </head>
    <body>
        <h1>Welcome to the Add User Page!</h1>
        <form method="post" action="AddUser">
            <input type="text" name="username-textbox" placeholder="Username" required pattern="[^\s]+" title="No whitespace"><br><br>
            <input type="password" name="password-textbox" placeholder="Password" required pattern="[^\s]+" title="No whitespace"><br><br>
            <input type="password" name="confirm-password-textbox" placeholder="Confirm Password" required pattern="[^\s]+" title="No whitespace"><br><br>
            <input name="register-button" type="submit" value="Add User"/>
        </form>
    </body>
</html>
