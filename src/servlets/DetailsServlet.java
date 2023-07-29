package servlets;

import db.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import models.Task;
import models.User;

@WebServlet(value = "/details")
public class DetailsServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Long id = Long.parseLong(req.getParameter("id"));
    Task task = DBUtil.getTaskById(id);
    req.setAttribute("zadanie", task);
    User user = (User) req.getSession().getAttribute("currentUser");
    if (user != null) {
      req.getRequestDispatcher("details.jsp").forward(req, resp);
    }
    req.getRequestDispatcher("signin.jsp").forward(req, resp);
  }
}
