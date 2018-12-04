<%-- 
    Document   : daily_report.jsp
    Created on : 02-Dec-2018, 11:31:08
    Author     : Paul
--%>

<%@page import="com.alphacab.model.Journey"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/resources/css/cdn_css.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Daily Report</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
            <h1 class="text-center display-4">Daily Report for 
                <%
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    out.print(sdf.format(date));
                %>
            </h1><br>
            <form method="post" action="DailyReport">
                <div class="input-group col-md-6 offset-md-3">
                <input type="text" class="form-control rounded-right" name="date-textbox" placeholder="yyyy-mm-dd"><span class="input-group-btn input-space">
                <button class="btn btn-primary" type="submit" name="get-bookings-button" value="Get Bookings">Get Bookings</button>
                </div><br><br>
                <%
                    if (request.getAttribute("error") != null) {
                        out.print(request.getAttribute("error"));
                    }
                %>

                <%
                    // servlet returns list of journeys
                    ArrayList<Journey> journeyList = (ArrayList) request.getAttribute("journeyList");

                    String stringDate = new SimpleDateFormat("yyyy-MM-dd").format((Date) request.getAttribute("date"));

                    if (journeyList != null && journeyList.size() > 0) { // prevents table headers being displayed if there are no results

                        out.print("<table class=\"table table-hover\">");
                        out.print("<tr>");
                        out.print("<th>Customer Name</th>");
                        out.print("<th>Driver</th>");
                        out.print("<th>Address</th>");
                        out.print("<th>Destination</th>");
                        out.print("<th>Distance</th>");
                        out.print("<th>Cost</th>");
                        out.print("</tr>");

                        float total = 0;

                        for (Journey journey : journeyList) {
                            out.print("<tr>");
                            out.print("<td>" + journey.getName() + "</td>");
                            out.print("<td>" + journey.getDriver().getRegistration() + "</td>");
                            out.print("<td>" + journey.getAddress() + "</td>");
                            out.print("<td>" + journey.getDestination() + "</td>");
                            out.print("<td>" + journey.getDistance() + "</td>");
                            out.print(String.format("<td>£%.2f</td>", journey.getCost()));
                            out.print("</tr>");

                            total += journey.getCost();
                        }

                        out.print("</table>");
                        // print number of journeys and total turnover below journey table
                        out.print("<br>Number of journeys served: " + journeyList.size());
                        out.print("<br>Total Turnover: £" + total);
                    } else {
                        out.print("No journeys found for " + stringDate);
                    }
                %>
            </form>
        </div>
        <jsp:include page="/resources/js/cdn_js.jsp"/>
    </body>
</html>
