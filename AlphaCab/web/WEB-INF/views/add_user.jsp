<%-- 
    Document   : register.jsp
    Created on : 05-Nov-2018, 15:35:32
    Author     : Paul
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="resources/css/main.css"/>
        <script type="text/javascript">
            function displayRoleInputs(value){
                if(value=="customer"){
                    hideDriverInput();
                    showCustomerInput();
                } else if(value=="driver"){
                    hideCustomerInput();
                    showDriverInput();
                } else {
                    hideCustomerInput();
                    hideDriverInput();
                }
            }
            
            function hideDriverInput() {
                document.getElementById('driver-input').style.display='none';
                document.getElementsByName('driver-name-textbox')[0].required = false;
                document.getElementsByName('registration-textbox')[0].required = false;
            }
            
            function showDriverInput() {
                document.getElementById('driver-input').style.display='block';
                document.getElementsByName('driver-name-textbox')[0].required = true;
                document.getElementsByName('registration-textbox')[0].required = true;
            }
            
            function hideCustomerInput() {
                document.getElementById('customer-input').style.display='none';
                document.getElementsByName('customer-name-textbox')[0].required = false;
                document.getElementsByName('address-textbox')[0].required = false;
            }
            
            function showCustomerInput() {
                document.getElementById('customer-input').style.display='block';
                document.getElementsByName('customer-name-textbox')[0].required = true;
                document.getElementsByName('address-textbox')[0].required = true;
            }
            
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add User</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h1>Welcome to the Add User Page!</h1>
        <form method="post" action="add_user">
        Select User Role: 
        <select name="role" onchange=displayRoleInputs(this.value);>
            <option value="admin">Admin</option>
            <option value="customer">Customer</option>
            <option value="driver">Driver</option>
        </select><br><br>
        <input type="text" name="username-textbox" placeholder="Username" required pattern="[^\s]+" title="No whitespace"><br><br>
            <input type="password" name="password-textbox" placeholder="Password" required pattern="[^\s]+" title="No whitespace"><br><br>
            <input type="password" name="confirm-password-textbox" placeholder="Confirm Password" required pattern="[^\s]+" title="No whitespace"><br><br>
        <div id="customer-input">
            <input type="text" name="customer-name-textbox" placeholder="Name"><br><br>
            <input type="text" name="address-textbox" placeholder="Address"><br><br>
        </div>
        <div id="driver-input">
            <input type="text" name="driver-name-textbox" placeholder="Name"><br><br>
            <input type="text" name="registration-textbox" placeholder="Registration"><br><br>
        </div>
            <input name="add-user-button" type="submit" value="Add User"/>
        </form>
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
