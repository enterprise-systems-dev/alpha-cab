<%-- 
    Document   : invoice
    Created on : 02-Dec-2018, 19:30:33
    Author     : Paul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/resources/css/cdn_css.jsp"/>
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
        <jsp:include page="/resources/js/cdn_js.jsp"/>
    </body>
</html>
