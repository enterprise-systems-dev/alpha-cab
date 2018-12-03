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
        <jsp:include page="/resources/css/cdn_css.jsp"/>
        <script type="text/javascript">
            function displayRoleInputs(value) {
                if (value == "customer") {
                    hideDriverInput();
                    showCustomerInput();
                } else if (value == "driver") {
                    hideCustomerInput();
                    showDriverInput();
                } else {
                    hideCustomerInput();
                    hideDriverInput();
                }
            }

            function hideDriverInput() {
                document.getElementById('driver-input').style.display = 'none';
                document.getElementsByName('driver-name-textbox')[0].required = false;
                document.getElementsByName('registration-textbox')[0].required = false;
            }

            function showDriverInput() {
                document.getElementById('driver-input').style.display = 'block';
                document.getElementsByName('driver-name-textbox')[0].required = true;
                document.getElementsByName('registration-textbox')[0].required = true;
            }

            function hideCustomerInput() {
                document.getElementById('customer-input').style.display = 'none';
                document.getElementsByName('customer-name-textbox')[0].required = false;
                document.getElementsByName('address-textbox')[0].required = false;
            }

            function showCustomerInput() {
                document.getElementById('customer-input').style.display = 'block';
                document.getElementsByName('customer-name-textbox')[0].required = true;
                document.getElementsByName('address-textbox')[0].required = true;
            }

        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add User</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
            <h1 class="text-center display-4">Welcome to the Add User Page!</h1><br>
            <form method="post" action="AddUser">
                <div class="form-group">
                    <label for="role">Select User Role</label>
                    <select class="custom-select" name="role" id="role" onchange=displayRoleInputs(this.value);>
                        <option value="admin">Admin</option>
                        <option value="customer">Customer</option>
                        <option value="driver">Driver</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="username-textbox">Username</label>
                    <input type="text" class="form-control" name="username-textbox" id="username-textbox" placeholder="Enter Username" required pattern="[^\s]+" title="No whitespace">
                </div>
                <div class="form-group">
                    <label for="password-textbox">Password</label>
                    <input type="password" class="form-control" name="password-textbox" id="password-textbox" placeholder="Enter Password" required pattern="[^\s]+" title="No whitespace">
                </div>
                <div class="form-group">
                    <label for="confirm-password-textbox">Confirm Password</label>
                    <input type="password" class="form-control" name="confirm-password-textbox" id="confirm-password-textbox" placeholder="Confirm Password" required pattern="[^\s]+" title="No whitespace">
                </div>
                <div id="customer-input">
                    <div class="form-group">
                        <label for="customer-name-textbox">Full Name</label>
                        <input type="text" class="form-control" name="customer-name-textbox" id="customer-name-textbox" placeholder="Enter Full Name">
                    </div>
                    <div class="form-group">
                        <label for="address-textbox">Address</label>
                        <input type="text" class="form-control" name="address-textbox" id="address-textbox" placeholder="Enter Address">
                    </div>
                </div>
                <div id="driver-input">
                    <div class="form-group">
                        <label for="driver-name-textbox">Full Name</label>
                        <input type="text" class="form-control" name="driver-name-textbox" id="driver-name-textbox" placeholder="Enter Full Name">
                    </div>
                    <div class="form-group">
                        <label for="registration-textbox">Car Registration</label>
                        <input type="text" class="form-control" name="registration-textbox" id="registration-textbox" placeholder="Enter Car Registration">
                    </div>
                </div>
                <div class="form-group">
                    <button class="btn btn-success" name="add-user-button" type="submit">Add User</button>
                    <a class="btn btn-danger float-right" href="RemoveUser">Remove User</a><br>
                </div>
            </form>
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
