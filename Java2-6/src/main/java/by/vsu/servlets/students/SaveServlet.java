package by.vsu.servlets.students;

import java.io.IOException;
import java.time.LocalDate;

import by.vsu.database.models.Student;
import by.vsu.database.storages.StudentsStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/students/save.html")
public class SaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            Integer group_id;
            try {
                group_id = Integer.parseInt(req.getParameter("group_id"));
            } catch (Exception e) {
                throw new IllegalArgumentException("Некорректный id группы: '" + req.getParameter("group_id") + "'!");
            }
            LocalDate birthday;
            try {
                birthday = LocalDate.parse(req.getParameter("birthday"));
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "Неверный формат дня рождения: '" + req.getParameter("birthday") + "'!");
            }
            Integer failedExams;
            try {
                failedExams = Integer.parseInt(req.getParameter("failedExams"));
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "Некорректное количество несданных экзаменов: '" + req.getParameter("failedExams") + "'!");
            }
            Student student = new Student(
                    group_id,
                    req.getParameter("last_name"),
                    req.getParameter("first_name"),
                    req.getParameter("middle_name"),
                    req.getParameter("sex"),
                    birthday,
                    req.getParameter("fundingType"),
                    failedExams);

            try {
                String id = req.getParameter("id");
                if (id == null) {
                    StudentsStorage.create(student);
                } else {
                    student.setId(Integer.parseInt(id));
                    StudentsStorage.update(student);
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректный id!");
            }

            resp.sendRedirect(req.getContextPath() + "/students/index.html");
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}