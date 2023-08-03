package servlets;

import db.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import models.User;

@WebServlet(value = "/sign-up")
public class SignupPageServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher("signup.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    var email = req.getParameter("email");
    var password = req.getParameter("password");
    var rePassword = req.getParameter("re_password");
    var fullName = req.getParameter("full_name");
    var age = Integer.parseInt(req.getParameter("age"));

    String redirect = "/sign-up?error=email";
    var user = DBUtil.getUserByEmail(email);
    if (user == null) {
      redirect = "/sign-up?error=password";
      if (password.equals(rePassword)) {
        user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setAge(age);
        DBUtil.addUser(user);
        redirect = "/sign-in";
      }
    }
    resp.sendRedirect(redirect);
  }
}
