package by.vsu.servlets;

import java.io.IOException;
import java.sql.SQLException;

import by.vsu.Book.Storage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete.html")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] ids = req.getParameterValues("id");
        if (ids == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Выберить хотя бы 1 книгу для удаления!");
            return;
        }
        for (String id : ids) {
            try {
                Storage.delete(Integer.parseInt(id));
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректный id!");
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/index.html");
        // sdfsdfsfsd
    }
}