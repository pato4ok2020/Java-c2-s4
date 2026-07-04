package by.vsu.servlets.users;

import java.io.IOException;
import java.sql.SQLException;

import by.vsu.database.models.User;
import by.vsu.database.storages.UsersStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/users/edit.html")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idParam = req.getParameter("id");

            if (idParam != null) {
                Integer id = Integer.parseInt(idParam);
                User user = UsersStorage.readById(id);
                req.setAttribute("editUser", user);
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректный id!");
            return;
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/jsp/users/edit.jsp").forward(req, resp);
    }
}