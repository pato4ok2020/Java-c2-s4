package by.vsu.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import by.vsu.database.models.User;
import by.vsu.database.storages.UsersStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login.html")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login != null && password != null) {
            try {
                User user = UsersStorage.checkUser(login, password);
                if (user != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);
                    resp.sendRedirect(req.getContextPath());
                } else {
                    String message = "Имя пользователя или пароль неопознанны";
                    String url = req.getContextPath() + "/login-form.jsp?message="
                            + URLEncoder.encode(message, "UTF-8");
                    resp.sendRedirect(url);
                }
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login-form.jsp");
        }
    }
}