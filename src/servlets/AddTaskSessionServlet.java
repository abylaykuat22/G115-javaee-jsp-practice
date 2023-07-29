package servlets;

import db.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Category;
import models.Performer;
import models.Task;

import java.io.IOException;

@WebServlet(value = "/add-task-session")
public class AddTaskSessionServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String name = req.getParameter("task_name");
    String description = req.getParameter("task_description");
    String deadlineDate = req.getParameter("task_deadlineDate");
    Long categoryId = Long.parseLong(req.getParameter("category_id"));
    Long performerId = Long.parseLong(req.getParameter("performer_id"));

    Task task = new Task();
    task.setName(name);
    task.setDescription(description);
    task.setDeadlineDate(deadlineDate);

    Category category = DBUtil.getCategoryById(categoryId);
    task.setCategory(category);

    Performer performer = DBUtil.getPerformerById(performerId);
    task.setPerformer(performer);

    HttpSession session = req.getSession();
    session.setAttribute("task", task);

    resp.sendRedirect("/add-task");
  }
}
