package by.vsu.servlets.users;

import java.io.IOException;

import by.vsu.database.models.User;
import by.vsu.database.storages.UsersStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/users/save.html")
public class SaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            User user = new User(
                    req.getParameter("login"),
                    req.getParameter("password"),
                    req.getParameter("role"));

            try {
                String id = req.getParameter("id");
                if (id == null) {
                    UsersStorage.create(user);
                } else {
                    user.setId(Integer.parseInt(id));
                    UsersStorage.update(user);
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректный id!");
            }
            resp.sendRedirect(req.getContextPath() + "/users/index.html");

        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}