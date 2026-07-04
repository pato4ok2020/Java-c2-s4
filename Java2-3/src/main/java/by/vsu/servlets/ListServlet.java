package by.vsu.servlets;

import java.io.IOException;
import java.util.Collection;

import by.vsu.Book.Book;
import by.vsu.Book.Storage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Book> books = Storage.readAll();
        req.setAttribute("books", books);
        getServletContext().getRequestDispatcher("/WEB-INF/index.html").forward(req, resp);
    }
}