package servlets;

import db.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.User;

@WebServlet(value = "/add-comment")
public class AddCommentServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String description = req.getParameter("description");
    Long blogId = Long.parseLong(req.getParameter("blog_id"));
    User user = (User) req.getSession().getAttribute("currentUser");
    DBUtil.addComment(description, blogId, user.getId());
    resp.sendRedirect("/blog-details?id="+blogId);
  }
}
