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
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addTaskModal">
    + Добавить задание
</button>
    <table class="table table-striped">
        <thead>
            <th>ID</th>
            <th>Наименование</th>
            <th>Крайний срок</th>
            <th>Выполнено</th>
            <th>Детали</th>
        </thead>
        <tbody>
        <%
            List<Task> tasks = (List<Task>) request.getAttribute("zadaniya");
            for (Task task : tasks) {
        %>
        <tr>
            <td><%=task.getId()%></td>
            <td><%=task.getName()%></td>
            <td><%=task.getDeadlineDate()%></td>
            <td><%=task.isStatus() ? "Да" : "Нет"%></td>
            <td><a class="btn btn-primary" href="#">Детали</a></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

<!-- Modal -->
<form action="/add-task" method="post">
<div class="modal fade" id="addTaskModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Новое задание</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <label>Наименование:</label>
                <input name="task_name" type="text" class="form-control">
                <label class="mt-3">Описание:</label>
                <textarea name="task_description" class="form-control"></textarea>
                <label class="mt-3">Крайний срок:</label>
                <input name="task_deadlineDate" type="date" class="form-control">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary">Добавить задание</button>
            </div>
        </div>
    </div>
</div>
</form>
</body>
</html>
