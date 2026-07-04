package by.vsu.servlets.groups;

import java.io.IOException;
import java.sql.SQLException;

import by.vsu.database.models.Group;
import by.vsu.database.storages.GroupsStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/groups/edit.html")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        try {
            if (idParam != null) {
                Integer id = Integer.parseInt(idParam);
                Group group = GroupsStorage.readById(id);
                req.setAttribute("group", group);
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректный id!");
            return;
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/jsp/groups/edit.jsp").forward(req, resp);
    }
}