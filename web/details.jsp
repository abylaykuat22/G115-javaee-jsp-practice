<%@ page import="java.util.List" %>
<%@ page import="models.Task" %><%--
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
    <%
        Task task = (Task) request.getAttribute("zadanie");
        if (task != null) {
    %>

    <h5>ID: <%=task.getId()%></h5>
    <h1>NAME: <%=task.getName()%></h1>
    <h5>DESCRIPTION: <%=task.getDescription()%></h5>
    <h5>DEADLINE DATE: <%=task.getDeadlineDate()%></h5>

    <%
        }
    %>

</body>
</html>
