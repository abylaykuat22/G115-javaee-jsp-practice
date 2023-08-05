<%@ page import="models.Blog" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Comment" %><%--
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
        Blog blog = (Blog) request.getAttribute("blog");
        if (blog != null) {
    %>
<div class="col-6 mx-auto" style="min-height: 1000px">
    <form action="/edit-blog" method="post">
    <label>ID:</label>
    <input readonly type="number" name="id" value="<%=blog.getId()%>" class="form-control"><br>
    <label>TITLE:</label>
    <input type="text" name="title" value="<%=blog.getTitle()%>" class="form-control"><br>
    <label>CONTENT:</label>
    <input type="text" name="content" value="<%=blog.getContent()%>" class="form-control"><br>
    <label>POST DATE:</label>
    <input type="date" readonly value="<%=blog.getPostDate()%>" class="form-control"><br>
    <label>AUTHOR:</label>
    <select name="user_id" class="form-select">
        <%
            List<User> users = (List<User>) request.getAttribute("users");
            for (User u : users) {
        %>
        <%
            if (blog.getUser().getId().equals(u.getId())) {
        %>
        <option selected value="<%=u.getId()%>"><%=u.getFullName()%></option>
        <%
            } else {
        %>
        <option value="<%=u.getId()%>"><%=u.getFullName()%></option>
        <%
                }
            }
        %>
    </select>
        <button class="btn btn-success">EDIT BLOG</button>
    </form>

    <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteBlogModal">
        Удалить
    </button>

    <form action="/delete-blog" method="post">
        <div class="modal fade" id="deleteBlogModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5">Вы уверены, что хотите удалить?</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <input type="hidden" value="<%=blog.getId()%>" name="blog_id">
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                        <button type="submit" class="btn btn-danger">Подтвердить удаление</button>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <%
        User currentUser = (User) session.getAttribute("currentUser");
    %>
    <form action="/add-comment" method="post">
        <label><%=currentUser.getFullName()%></label>
        <input type="hidden" value="<%=blog.getId()%>" name="blog_id">
        <textarea name="description" class="form-control" rows="5"></textarea>
        <button class="btn btn-primary">ADD COMMENT</button>
    </form>
    <%
        List<Comment> comments = (List<Comment>) request.getAttribute("comments");
        for (Comment comment : comments) {
    %>
    <div style="display: flex; justify-content: space-between">
        <div>
            <label><%=comment.getUser().getFullName()%> add comment at <%=comment.getPostDate()%></label>
            <p><%=comment.getDescription()%></p>
        </div>
        <div>
            <%
             if (comment.getUser().getId().equals(user.getId())) {
            %>
            <form action="/delete-comment" method="post">
                <input type="hidden" value="<%=blog.getId()%>" name="blog_id">
                <input type="hidden" value="<%=comment.getId()%>" name="comment_id">
                <button class="btn btn-danger">X</button>
            </form>
            <%
                }
            %>
        </div>
    </div>
    <%
        }
    %>
</div>
    <%
        }
    %>
</body>
</html>
