package servlets;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/add-task")
public class AddTaskServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String name = req.getParameter("task_name");
    String description = req.getParameter("task_description");
    String deadlineDate = req.getParameter("task_deadlineDate");
    DBManager.addTask(name, description, deadlineDate);
    resp.sendRedirect("/");
  }
}
