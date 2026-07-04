package by.vsu.servlets.students;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import by.vsu.database.models.Student;
import by.vsu.database.storages.StudentsStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/students/index.html")
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idParam = req.getParameter("id");
            String sortParam = req.getParameter("sort");
            Collection<Student> students;

            if (idParam != null) {
                if (sortParam != null) {
                    students = StudentsStorage.readByGroupId(Integer.parseInt(idParam), sortParam);
                } else {
                    students = StudentsStorage.readByGroupId(Integer.parseInt(idParam));
                }
            } else {
                if (sortParam != null) {
                    students = StudentsStorage.readAll(sortParam);
                } else {
                    students = StudentsStorage.readAll();
                }
            }

            req.setAttribute("students", students);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/students/list.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}