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
    <h5>CATEGORY: <%=task.getCategory().getName()%></h5>
    <h5>PERFORMER: <%=task.getPerformer().getName()%></h5>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editTaskModal">
    Редактировать
</button>

<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteTaskModal">
    Удалить
</button>

<form action="/edit-task" method="post">
    <div class="modal fade" id="editTaskModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Редактирование задания</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" value="<%=task.getId()%>" name="task_id">
                    <label>Наименование:</label>
                    <input value="<%=task.getName()%>" name="task_name" type="text" class="form-control">
                    <label class="mt-3">Описание:</label>
                    <textarea name="task_description" class="form-control"><%=task.getDescription()%></textarea>
                    <label class="mt-3">Крайний срок:</label>
                    <input value="<%=task.getDeadlineDate()%>" name="task_deadlineDate" type="date" class="form-control">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Внести изменения</button>
                </div>
            </div>
        </div>
    </div>
</form>

<form action="/delete-task" method="post">
    <div class="modal fade" id="deleteTaskModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5">Вы уверены, что хотите удалить?</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <input type="hidden" value="<%=task.getId()%>" name="task_id">
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                    <button type="submit" class="btn btn-danger">Подтвердить удаление</button>
                </div>
            </div>
        </div>
    </div>
</form>

<%
    }
%>
</body>
</html>
