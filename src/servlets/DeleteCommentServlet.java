package servlets;

import db.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/delete-comment")
public class DeleteCommentServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Long commentId = Long.parseLong(req.getParameter("comment_id"));
    Long blogId = Long.parseLong(req.getParameter("blog_id"));
    DBUtil.deleteCommentById(commentId);
    resp.sendRedirect("/blog-details?id="+blogId);
  }
}
