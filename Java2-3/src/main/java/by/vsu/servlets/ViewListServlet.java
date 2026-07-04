package by.vsu.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import by.vsu.Book.Book;
import by.vsu.Book.Storage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ViewListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        @SuppressWarnings("unchecked")
        Collection<Book> books = (Collection<Book>) req.getAttribute("books");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter w = resp.getWriter();
        w.println("<HTML>");
        w.println("<HEAD>");
        w.println("<META http-equiv=\"Content-Type\"");
        w.println("content=\"text/html; charset=UTF-8\">");
        w.println("<TITLE> Библиотека </TITLE>");
        w.println("<STYLE>");
        w.println("TABLE {");
        w.println("border-collapse: collapse;");
        w.println("}");
        w.println("TH, TD {");
        w.println("border: 1px solid black;");
        w.println("padding: 5px 30px 5px 10px;");
        w.println("}");
        w.println("</STYLE>");
        w.println("</HEAD>");
        w.println("<BODY>");
        w.println("<FORM action=\"delete.html\" method=\"post\">");
        w.println("<TABLE>");
        w.print("<TR>");
        w.print("<TH>&nbsp;</TH>");
        w.print("<TH> Шифр </TH>");
        w.print("<TH> Название </TH>");
        w.print("<TH> Автор </TH>");
        w.print("<TH> Год издания </TH>");
        w.print("<TH> Кол-во страниц </TH>");
        w.print("<TH> Кол-во ссылок </TH>");
        w.print("<TH> Индекс цитирования </TH>");
        w.println("</TR>");
        for (Book book : books) {
            w.print("<TR>");
            w.print("<TD>");
            w.printf(
                "<INPUT type=\"checkbox\" name=\"id\" value=\"%d\">",
                book.getId()
            );
            w.printf("</TD>");

            w.printf(
                "<TD><A href=\"edit.html?id=%d\">%s</A></TD>",
                book.getId(), book.getCode()
            );
            w.printf("<TD>%s</TD>", book.getTitle());
            w.printf("<TD>%s</TD>", book.getAuthor());
            w.printf("<TD>%d</TD>", book.getYear());
            w.printf("<TD>%d</TD>", book.getAmountPages());
            w.printf("<TD>%d</TD>", book.getAmountLinks());
            w.printf("<TD>%.2f</TD>", book.getCitationIndex());
            w.println("</TR>");
        }
        w.println("</TABLE>");

        w.println("<P>");
        w.println("<A href=\"edit.html\">Добавить</A>");
        w.println("<BUTTON type=\"submit\">Удалить</BUTTON>");
        w.println("</P>");
        w.println("</FORM>");

        w.println("<P>");
        w.printf(
            "Самые лучшие авторы: %s",
            String.join(", ", Storage.getBestAuthors(books))
        );
        w.println("</P>");
        
        w.println("</BODY>");
        w.println("</HTML>");
    }
}