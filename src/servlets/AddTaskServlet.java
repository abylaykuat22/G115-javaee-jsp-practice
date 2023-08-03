package servlets;

import db.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Task;

import java.io.IOException;

@WebServlet(value = "/add-task")
public class AddTaskServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher("addTaskConfirm.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String name = req.getParameter("task_name");
    String description = req.getParameter("task_description");
    String deadlineDate = req.getParameter("task_deadlineDate");
    Long categoryId = Long.parseLong(req.getParameter("category_id"));
    Long performerId = Long.parseLong(req.getParameter("performer_id"));
    DBUtil.addTask(name, description, deadlineDate, categoryId, performerId);
    resp.sendRedirect("/");
  }
}
