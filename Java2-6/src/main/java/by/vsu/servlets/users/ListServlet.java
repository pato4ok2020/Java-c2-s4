package by.vsu.servlets.users;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import by.vsu.database.models.User;
import by.vsu.database.storages.UsersStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/users/index.html")
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String sortParam = req.getParameter("sort");
            Collection<User> users;
            if (sortParam != null) {
                users = UsersStorage.readAll(sortParam);
            } else {
                users = UsersStorage.readAll();
            }
            req.setAttribute("users", users);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/users/list.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}