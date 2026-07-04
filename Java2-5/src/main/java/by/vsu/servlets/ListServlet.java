package by.vsu.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import by.vsu.Book.Book;
import by.vsu.Book.Storage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/index.html")
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Collection<Book> books = Storage.readAll();
            req.setAttribute("books", books);
            req.setAttribute("bestAuthors", Storage.getBestAuthors(books));
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}