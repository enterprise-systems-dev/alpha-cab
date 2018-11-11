<%-- 
    Document   : index
    Created on : 05-Nov-2018, 13:13:42
    Author     : Paul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h1>WELCOME TO THE HOME PAGE!</h1>
        Hello, <%out.print(session.getAttribute("user"));%><br><br>           
        <form method="get" action="AddUser">
            <input name="add-user-button" type="submit" value="Add User">
        </form>
    </body>
</html>
