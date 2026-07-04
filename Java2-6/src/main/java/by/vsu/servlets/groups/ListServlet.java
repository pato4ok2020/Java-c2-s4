package by.vsu.servlets.groups;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import by.vsu.database.models.Group;
import by.vsu.database.storages.GroupsStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/groups/index.html")
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String sortParam = req.getParameter("sort");
            Collection<Group> groups;
            if (sortParam != null) {
                groups = GroupsStorage.readAll(sortParam);
            } else {
                groups = GroupsStorage.readAll(null);
            }
            req.setAttribute("groups", groups);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/groups/list.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}