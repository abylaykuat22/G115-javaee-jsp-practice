package servlets;

import db.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import models.User;

@WebServlet(value = "/blogs")
public class BlogsServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    User user = (User) req.getSession().getAttribute("currentUser");
    if (user != null) {
      req.setAttribute("novosti", DBUtil.getBlogs());
      req.getRequestDispatcher("blogs.jsp").forward(req, resp);
    }
    resp.sendRedirect("/sign-in");
  }
}
