package servlets;

import db.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.User;

@WebServlet(value = "/delete-blog")
public class BlogDeleteServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Long id = Long.parseLong(req.getParameter("blog_id"));
    DBUtil.deleteBlogById(id);
    resp.sendRedirect("/blogs");
  }
}
