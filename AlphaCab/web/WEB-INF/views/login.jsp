<%-- 
    Document   : login
    Created on : 04-Nov-2018, 17:46:50
    Author     : Paul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/resources/css/cdn_css.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <div class="container col-md-6">
            <div class="jumbotron">
            <h1 class="text-center display-4">Welcome to AlphaCab!</h1>
            </div>
            <div class="col-md-6 offset-md-3">
                <form method="post" name="login-form" action="Login">
                    <div class="form-group">
                        <input type="text" class="form-control" id="username-textbox" name="username-textbox" placeholder="Enter Username" required pattern="[^\s]+" 
                           oninvalid="this.setCustomValidity('No whitespace allowed')" oninput="this.setCustomValidity('')">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="password-textbox" name="password-textbox" placeholder="Enter Password" required pattern="[^\s]+" 
                           oninvalid="this.setCustomValidity('No whitespace allowed')" oninput="this.setCustomValidity('')">
                    </div>
                    
                    <input class="btn btn-primary float-right" name="login-button" type="submit" value="Login"/><br><br>
                    <%
                        if(request.getAttribute("error") != null) {                            
                            out.print("<div class=\"alert alert-info alert-dismissible\">");
                            out.print("<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>");
                            out.print(request.getAttribute("error"));
                            out.print("</div>");
                        }
                    %>
                </form>
                <br>
                
                <form class="text-center" method="post" name="guest-login-form" action="Login">
                    <label for="guest-login"><small>Don`t want to register but still need an AlphaCab?</small></label>
                    <input type="hidden" name="guest-form" value="guest-login-form">
                    <button type="submit" class="btn btn-sm btn-light" name="guest-login" id="guest-login"><small>Get an emergency Noober</small></button>
                </form>
            </div>
        </div>
        <jsp:include page="/resources/js/cdn_js.jsp"/>
    </body>
</html>
