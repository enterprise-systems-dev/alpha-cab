<%-- 
    Document   : invoice
    Created on : 02-Dec-2018, 19:30:33
    Author     : Paul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Invoice</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container col-md-6">
        <h1 class="text-center">New Invoice</h1>
        <form method="post">
            <div class="row">           
                <div class="col form-group">
                <select class="custom-select custom-select-inline">
                    <option selected>Select Customer</option>
                    <!--
                    Servlet will get list of customers and list will be created from them
                    -->
                    <option value="1">Customer 1</option>
                    <option value="2">Customer 2</option>
                    <option value="3">Customer 3</option>
                </select>
                </div>
               <div class="col">
                <select class="custom-select custom-select-inline">
                    <option selected>Select Driver</option>
                    <!--
                    Servlet will get list of drivers and list will be created from them
                    -->
                    <option value="1">Driver 1</option>
                    <option value="2">Driver 2</option>
                    <option value="3">Driver 3</option>
                </select>       
               </div>
            </div>
            <div class="form-group">
                <label for="address-textbox">Address</label>
                <input type="text" class="form-control" id="address-textbox" name="address-textbox" placeholder="Enter Address">
            </div>
            <div class="form-group">
                <label for="destination-textbox">Destination</label>
                <input type="text" class="form-control" id="destination-textbox" name="destination-textbox" placeholder="Enter Destination">
            </div>
            <div class="form-group custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input" id="vat-checkbox">
                <label class="custom-control-label" for="vat-checkbox"> VAT</label>
            </div>
            <div class="form-group">
            <button type="submit" class="btn btn-success">Create Invoice</button>
            </div>
        </form>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
