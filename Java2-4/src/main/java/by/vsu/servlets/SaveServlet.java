package by.vsu.servlets;

import java.io.IOException;

import by.vsu.Book.Book;
import by.vsu.Book.Storage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/save.html")
public class SaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        try {
            Book book = new Book(
                req.getParameter("code"),
                req.getParameter("author"),
                req.getParameter("title"),
                Integer.parseInt(req.getParameter("year")),
                Integer.parseInt(req.getParameter("amount-pages")),
                Integer.parseInt(req.getParameter("amount-links"))
            );

            try {
                String id = req.getParameter("id");
                if (id == null) {
                    Storage.create(book);
                } else {
                    book.setId(Integer.parseInt(id));
                    Storage.update(book);
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректный id!");
            }

            resp.sendRedirect(req.getContextPath() + "/index.html");
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка на стороне сервера!");
        }
    }
}   