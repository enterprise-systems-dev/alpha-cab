<%-- 
    Document   : remove_user
    Created on : 26-Nov-2018, 01:48:54
    Author     : Darren
--%>

<%@page import="com.alphacab.model.User"%>
<%@page import="com.alphacab.model.Admin"%>
<%@page import="com.alphacab.model.Driver"%>
<%@page import="com.alphacab.model.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remove User</title>
    </head>
    <body>
        <h1>Welcome to the remove user page</h1>
        User List<br>
        <table style="width:100%">
            <tr>
                <th>userID</th>
                <th>Username</th>
                <th>Role</th>
            </tr>
            <%
                ArrayList<User> userList = (ArrayList)request.getAttribute("userList");
                for(User u : userList){
                    out.print("<tr>");
                    out.print("<td>" + u.getId() + "</td>");
                    out.print("<td>" + u.getUsername() + "</td>");
                    out.print("<td>" + u.getRole() + "</td>");
                    out.print("</tr>");
                }
            %>
        </table>
        <form method="post" action="RemoveUser">
            <br>Select ID<br>
            <select name="userID">
                <%
                    for(User u : userList)
                        out.print("<option value=" +u.getId()+ ">" +u.getId()+ "</option>");
                %>
            </select>
            <input type="submit" value="Remove User">
        </form>
        <br><br>
        <a href="AddUser">Add User</a><br>
        <a href="Logout">Log out</a>
        <br><br>
        <%
            if(request.getAttribute("error") != null) {
                out.print(request.getAttribute("error"));
            }
            if(request.getAttribute("message") != null) {
                out.print(request.getAttribute("message"));
            }
        %>
    </body>
</html>
