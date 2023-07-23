package servlets;

import db.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.Task;

@WebServlet(value = "/edit-task")
public class EditTaskServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Long id = Long.parseLong(req.getParameter("task_id"));
    String name = req.getParameter("task_name");
    String description = req.getParameter("task_description");
    String deadlineDate = req.getParameter("task_deadlineDate");

    Task task = DBUtil.getTaskById(id);
    if (task != null) {
      task.setName(name);
      task.setDescription(description);
      task.setDeadlineDate(deadlineDate);
    }
    DBUtil.editTask(task);
    resp.sendRedirect("/details?id="+id);
  }
}
