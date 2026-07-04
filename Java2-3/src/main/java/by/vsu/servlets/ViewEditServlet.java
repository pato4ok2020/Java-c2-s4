package by.vsu.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import by.vsu.Book.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ViewEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = (Book) req.getAttribute("book");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter w = resp.getWriter();
        w.println("<HTML>");
        w.println("<HEAD>");
        w.println("<META http-equiv=\"Content-Type\"");
        w.println("content=\"text/html; charset=UTF-8\">");
        w.println("<TITLE> Библиотека </TITLE>");
        w.println("</HEAD>");
        w.println("<BODY>");
        w.println("<FORM action=\"save.html\" method=\"post\">");
        if (book != null) {
            w.printf(
                "<INPUT type=\"hidden\" name=\"id\" value=\"%d\">\n", 
                book.getId()
            );
        }

        w.println("<P> Шифр :</P>");
        w.printf(
            "<INPUT type=\"text\" name=\"code\" value=\"%s\">\n",
            book != null ? book.getCode() : ""
        );

        w.println("<P> Автор: </P>");
        w.printf(
            "<INPUT type=\"text\" name=\"author\" value=\"%s\">\n",
            book != null ? book.getAuthor() : ""
        );

        w.println("<P> Название: </P>");
        w.printf(
            "<INPUT type=\"text\" name=\"title\" value=\"%s\">\n",
            book != null ? book.getTitle() : ""
        );

        w.println("<P> Год издания: </P>");
        w.printf(
            "<INPUT type=\"number\" name=\"year\" value=\"%s\">\n",
            book != null ? book.getYear() : 2026
        );

        w.println("<P> Кол-во страниц: </P>");
        w.printf(
            "<INPUT type=\"number\" name=\"amount-pages\" value=\"%s\">\n",
            book != null ? book.getAmountPages() : 1
        );

        w.println("<P> Кол-во ссылок: </P>");
        w.printf( 
            "<INPUT type=\"number\" name=\"amount-links\" value=\"%s\"><br>",
            book != null ? book.getAmountLinks() : 0
        );
        
        w.println("<BUTTON type=\"submit\">Сохранить</BUTTON>");
        w.println("<A href=\"index.html\">Назад</A>");
        w.println("</FORM>");
        w.println("</BODY>");
        w.println("</HTML>");
    }
}