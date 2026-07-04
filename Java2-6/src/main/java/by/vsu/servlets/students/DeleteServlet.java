package by.vsu.servlets.students;

import java.io.IOException;
import java.sql.SQLException;
import by.vsu.database.storages.StudentsStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/students/delete.html")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] ids = req.getParameterValues("id");
        if (ids == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Выберить хотя бы 1 студента для удаления!");
            return;
        }

        for (String id : ids) {
            try {
                StudentsStorage.delete(Integer.parseInt(id));
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректный id: " + id + "!");
            } catch (SQLException e) {
                throw new ServletException(e);
            }
            
            resp.sendRedirect(req.getContextPath() + "/students/index.html");
        }
    }
}