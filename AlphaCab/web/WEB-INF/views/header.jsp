<%-- 
    Document   : header
    Created on : 13-Nov-2018, 16:40:07
    Author     : Paul
--%>

<%@page import="com.alphacab.model.User"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="index.jsp">AlphaCab</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-auto">
        <%
            User sessionUser = (User) session.getAttribute("user");
            if (sessionUser.getRole().equalsIgnoreCase("admin")) {
        %>
        <li class="nav-item active dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Users
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="AddUser">Add User</a>
          <a class="dropdown-item" href="RemoveUser">Remove User</a>
        </div>
      </li>
        <li class="nav-item active">
            <a class="nav-link" href="Customers">Customers<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="Drivers">Drivers<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="DailyReport">Daily Report<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="AssignJobs">Assign Job<span class="sr-only">(current)</span></a>
        </li>
        <%}%>
        <li class="nav-item">
            <a class="nav-link" href="Logout">Log Out<span class="sr-only">(current)</span></a>
        </li>
    </ul>
  </div>
</nav><br>
