package by.vsu.servlets.students;

import java.io.IOException;
import java.sql.SQLException;

import by.vsu.database.models.Student;
import by.vsu.database.storages.StudentsStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/students/edit.html")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idParam = req.getParameter("id");

            if (idParam != null) {
                Integer id = Integer.parseInt(idParam);
                Student student = StudentsStorage.readById(id);
                req.setAttribute("student", student);
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректный id!");
            return;
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/jsp/students/edit.jsp").forward(req, resp);
    }
}