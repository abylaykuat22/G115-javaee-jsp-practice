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
    <%@include file="head.jsp" %>
</head>
<body>
<%@include file="navbar.jsp" %>
<%
    Task task = (Task) session.getAttribute("task");
    if (task != null) {
%>
<form action="/add-task" method="post">
    <input value="<%=task.getName()%>" name="task_name" type="text" class="form-control" readonly>
    <label class="mt-3">Описание:</label>
    <textarea name="task_description" class="form-control" readonly><%=task.getDescription()%></textarea>
    <label class="mt-3">Крайний срок:</label>
    <input value="<%=task.getDeadlineDate()%>" name="task_deadlineDate" type="date" readonly class="form-control">
    <label class="mt-3">Категория:</label>
    <select class="form-select" name="category_id" readonly="">
        <option value="<%=task.getCategory().getId()%>"><%=task.getCategory().getName()%>
        </option>
    </select>
    <label class="mt-3">Исполнитель:</label>
    <select class="form-select" name="performer_id" readonly="">
        <option value="<%=task.getPerformer().getId()%>"><%=task.getPerformer().getName()%>
        </option>
    </select><br>
    <button class="btn btn-success">Добавить задание</button>
</form>
<%
    }
%>
</body>
</html>
