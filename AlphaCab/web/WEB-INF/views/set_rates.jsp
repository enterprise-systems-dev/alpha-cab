<%-- 
    Document   : set_rates
    Created on : 04-Dec-2018, 08:00:22
    Author     : Paul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/resources/css/cdn_css.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Set Rates</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/header.jsp"/>
        <div class="container">
            <h1 class="text-center display-4">Rates</h1>
            <table class="table col-md-6 offset-md-3 text-center">
                <thead>
                    <tr>
                        <th>Current Base Rate</th>
                        <th>Current Rate Per Mile</th>
                    </tr>
                </thead>
                <tr>
                    <td><%out.print(request.getAttribute("base-rate"));%></td>
                    <td><%out.print(request.getAttribute("rate-per-mile"));%></td>
                </tr>
            </table>
            <div class="row col-md-6 offset-md-3">
                <label for="base-rate-textbox">Enter new base rate</label>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">£</span>
                    </div>
                    <input type="text" name="base-rate-textbox" id="base-rate-textbox" class="form-control" aria-label="Amount (to the nearest dollar)">
                </div>
                <label for="per-mile-textbox">Enter new price per mile</label>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">£</span>
                    </div>
                    <input type="text" name="per-mile-textbox" id="per-mile-textbox" class="form-control" aria-label="Amount (to the nearest dollar)">
                </div>
            </div>
                <form method="post" name="change-rates-form" action="SetRates">
                <input  class="btn btn-primary float-right" name="change-rates-button" type="submit" value="SetRates"/>
                </form>
            <jsp:include page="/resources/js/cdn_js.jsp"/>
    </body>
</html>
