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
        <jsp:include page="/resources/css/cdn_css.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remove User</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
        <h1 class="text-center display-4">Welcome to the remove user page</h1>
        <% ArrayList<User> userList = (ArrayList)request.getAttribute("userList");
            out.print("<div class=\"text-center\">" + userList.size() + " users found </div><br>");
        %>
        
        <form method="post" action="RemoveUser">
            <label for="userID">Select User to Remove</label>
            <div class="form-group">         
                <select class="custom-select form-control col-md-9" name="userID" id="userID">
                    <%
                        for(User u : userList)
                            out.print("<option value=" +u.getId()+ ">" +u.getId()+ "</option>");
                    %>
                </select>
 
                    <button class="btn btn-danger float-right col-md-2" type="submit" value="Remove User">Remove User</button>
 
            </div>
        </form>
        <table class="table table-hover">
            <thead class="thead-light">
                <tr>
                    <th>userID</th>
                    <th>Username</th>
                    <th>Role</th>
                </tr>
            </thead>
            <%
                for(User u : userList){
                    out.print("<tr>");
                    out.print("<td>" + u.getId() + "</td>");
                    out.print("<td>" + u.getUsername() + "</td>");
                    out.print("<td>" + u.getRole() + "</td>");
                    out.print("</tr>");
                }
            %>
        </table>
        
        <%
            if(request.getAttribute("error") != null) {
                out.print(request.getAttribute("error"));
            }
            if(request.getAttribute("message") != null) {
                out.print(request.getAttribute("message"));
            }
        %>
        </div>
        <jsp:include page="/resources/js/cdn_js.jsp"/>
    </body>
</html>
