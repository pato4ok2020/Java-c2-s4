package by.vsu.servlets.groups;

import java.io.IOException;

import by.vsu.database.models.Group;
import by.vsu.database.storages.GroupsStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/groups/save.html")
public class SaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        try {
            Integer course;
            try {
                course = Integer.parseInt(req.getParameter("course"));
            } catch (Exception e) {
                throw new IllegalArgumentException("Некорректный номер курса: '" + req.getParameter("course") + "'!");
            }
            Group group = new Group(
                    req.getParameter("name"),
                    0,
                    req.getParameter("specialization"),
                    course,
                    req.getParameter("studyForm"));

            try {
                String id = req.getParameter("id");
                if (id == null || id.isBlank()) {
                    GroupsStorage.create(group);
                } else {
                    group.setId(Integer.parseInt(id));
                    GroupsStorage.update(group);
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректный id!");
            }

            resp.sendRedirect(req.getContextPath() + "/groups/index.html");
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}