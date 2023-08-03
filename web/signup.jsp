<%@ page import="java.util.List" %>
<%@ page import="models.Task" %>
<%@ page import="models.Category" %>
<%@ page import="models.Performer" %><%--
  Created by IntelliJ IDEA.
  User: Kuat
  Date: 18.07.2023
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container col-6 mx-auto">
    <%
        String error = request.getParameter("error");
        if (error != null) {
          if (error.equals("email")) {
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <strong>Email is busy!</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
        } else if (error.equals("password")) {
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <strong>Passwords are not same!</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
            }
        }
    %>
    <form action="/sign-up" method="post">
        <div class="mb-3">
            <label class="form-label">Email address</label>
            <input name="email" type="email" class="form-control" aria-describedby="emailHelp">
        </div>
        <div class="mb-3">
            <label class="form-label">Password</label>
            <input name="password" type="password" class="form-control">
        </div>
        <div class="mb-3">
            <label class="form-label">Retype Password</label>
            <input name="re_password" type="password" class="form-control">
        </div>
        <div class="mb-3">
            <label class="form-label">Full name</label>
            <input name="full_name" type="text" class="form-control">
        </div>
        <div class="mb-3">
            <label class="form-label">Age</label>
            <input name="age" type="number" class="form-control">
        </div>
        <button class="btn btn-success">CREATE NEW ACCOUNT</button>
    </form>
</div>
</body>
</html>
