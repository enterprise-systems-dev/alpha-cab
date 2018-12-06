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
        <jsp:include page="/resources/css/cdn_css.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/header.jsp"/>
        <div class="container">
            <h1 class="text-center display-4">Welcome to the Home Page!</h1>
            <%  User sessionUser = (User) session.getAttribute("user");%>
            <div class="alert alert-success text-center col-md-6 offset-md-3" role="alert">
                <%out.print(String.format("Hello %s, your user type is %s and id is %d",
                            sessionUser.getUsername(), sessionUser.getRole(), sessionUser.getId()));%>
            </div>  
            <%
                if (sessionUser.getRole().equalsIgnoreCase("customer")) {
            %>
            <form method="post" action="CallAlphaCab">
                <div class="form-group">
                    <label for="name-textbox">Name</label>
                    <input type="text" class="form-control" name="name-textbox" id="name-textbox" placeholder="Enter name">
                </div>                
                <div class="form-group">
                    <label for="address-textbox">Address</label>
                    <input type="text" class="form-control" name="address-textbox" id="address-textbox" placeholder="Enter current address">
                </div>
                <div class="form-group">
                    <label for="destination-textbox">Destination</label>
                    <input type="text" class="form-control" name="destination-textbox" id="destination-textbox" placeholder="Enter destination address">
                </div>
                <div class="form-group">
                    <label for="pickup-date">Date</label>
                    <input type="text" class="form-control" name="pickup-date" id="pickup-date" placeholder="yyyy-MM-dd" pattern="^\d{4}-((0\d)|(1[012]))-(([012]\d)|3[01])$">
                </div>
                <div class="form-group">
                    <label for="pickup-time">Time</label>
                    <input type="text" class="form-control" name="pickup-time" id="pickup-time" placeholder="hh:mm:ss - 24h format">
                </div>
                <%-- try this for date and time https://jqueryui.com/datepicker/ 
                     make sure date and time are in specified format 
                     thats how the database wants it.
                --%>
                <input class="btn btn-success" type="submit" name="call-AlphaCab-button" value="Call AlphaCab">
            </form><br>
            <form method="get" action="CallAlphaCab">
                <input class="btn btn-info" type="submit" name="view-AlphaCab-history-button" value="AlphaCab History">
            </form>
            <%}%>
            <%
                if (request.getAttribute("error") != null) {
                    out.print(request.getAttribute("error"));
                }
                if (request.getAttribute("message") != null) {
                    out.print(request.getAttribute("message"));
                }
            %>
        </div>
        <jsp:include page="/resources/js/cdn_js.jsp"/>
    </body>
</html>
