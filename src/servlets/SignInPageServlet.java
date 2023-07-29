package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.io.IOException;

@WebServlet(value = "/sign-in")
public class SignInPageServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    User user = (User) req.getSession().getAttribute("currentUser");
    if (user == null) {
      req.getRequestDispatcher("signin.jsp").forward(req, resp);
    }
    resp.sendRedirect("/");
  }
}
