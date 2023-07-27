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

@WebServlet(value = "/")
public class HomeServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setAttribute("zadaniya", DBUtil.getTasks());
    req.setAttribute("categories", DBUtil.getCategories());
    req.setAttribute("performers", DBUtil.getPerformers());
    req.getRequestDispatcher("home.jsp").forward(req, resp);
  }
}
