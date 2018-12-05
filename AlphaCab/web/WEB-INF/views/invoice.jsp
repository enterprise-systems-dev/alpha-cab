<%-- 
    Document   : invoice
    Created on : 02-Dec-2018, 19:30:33
    Author     : Paul
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/resources/css/cdn_css.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invoice</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container col-md-6">
            <h1 class="text-center display-4">Invoice</h1>
            <div class="row">
                <div class="col">
                    <strong>Name</strong><br>
                    <%out.print(request.getAttribute("name").toString());%>
                </div>
            </div>
            <div class="row">           
                <div class="col">
                    <br><strong>Address</strong><br>
                    <%out.print(request.getAttribute("address"));%>
                </div>
                <div class="col text-right">
                    <br><strong>Destination</strong><br>
                    <%out.print(request.getAttribute("destination"));%>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <br><strong>Journey Date/Time</strong><br>
                    <%out.print(request.getAttribute("date") + " " + request.getAttribute("time"));%>
                </div>
                <div class="col text-right">
                    <br><strong>Booking Date/Time</strong><br>
                    <%
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        Date date = new Date();
                        out.print(sdf.format(date));
                    %>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title"> </h3>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-condensed">
                                    <thead>
                                        <tr>
                                            <td><strong>Distance</strong></td>
                                            <td class="text-center"><strong>Price Per Mile</strong></td>
                                            <td class="text-right"><strong>Totals</strong></td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                        <tr>
                                            <td><%out.print(request.getAttribute("distance"));%></td>
                                            <td class="text-center"><%out.print(String.format("£%.2f", Double.parseDouble(request.getAttribute("rate-per-mile").toString())));%></td>
                                            <td class="text-right"><%out.print(String.format("£%.2f", Double.parseDouble(request.getAttribute("distanceCost").toString())));%></td>
                                        </tr>
                                        <tr>
                                        <tr>
                                            <td class="thick-line"></td>
                                            <td class="thick-line text-center"><strong>Base Rate</strong></td>
                                            <td class="thick-line text-right"><%out.print(String.format("£%.2f", Double.parseDouble(request.getAttribute("base-rate").toString())));%></td>
                                        </tr>
                                        <tr>
                                            <td class="no-line"></td>
                                            <td class="no-line text-center"><strong>Total (ex. VAT)</strong></td>
                                            <td class="no-line text-right"><%out.print(String.format("£%.2f", Double.parseDouble(request.getAttribute("totalExVAT").toString())));%></td>
                                        </tr>
                                        <tr>
                                            <td class="no-line"></td>
                                            <td class="no-line text-center"><strong>Total (inc. VAT)</strong></td>
                                            <td class="no-line text-right"><%out.print(String.format("£%.2f", Double.parseDouble(request.getAttribute("totalIncVAT").toString())));%></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/resources/js/cdn_js.jsp"/>
    </body>
</html>
