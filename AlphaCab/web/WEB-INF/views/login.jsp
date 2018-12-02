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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <div class="container col-md-6">
            <div class="jumbotron">
            <h1 class="text-center display-4">Welcome to Noober!</h1>
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
                            out.print(request.getAttribute("error"));
                        }
                    %>
                </form>
                <br>
                
                <form class="text-center" method="post" name="guest-login-form" action="Login">
                    <label for="guest-login"><small>Don`t want to register but still need a Noober?</small></label>
                    <input type="hidden" name="guest-form" value="guest-login-form">
                    <button type="submit" class="btn btn-sm btn-light" name="guest-login" id="guest-login"><small>Get an emergency Noober</small></button>
                </form>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
