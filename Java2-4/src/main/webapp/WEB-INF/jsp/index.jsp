<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Библиотека: главная </title>
        <style>
            table {
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid black;
                padding: 5px 30px 5px 10px;
            }
        </style>
    </head>
    <body>
        <form action="delete.html" method="post">
            <table>
                <tr>
                    <th> &nbsp; </th>
                    <th> Шифр </th>
                    <th> Название </th>
                    <th> Автор </th>
                    <th> Год издания </th>
                    <th> Кол-во страниц </th>
                    <th> Кол-во ссылок </th>
                    <th> Индекс цитирования </th>
                </tr>

                <c:forEach var="book" items="${books}">
                    <TR>
                        <TD>
                            <INPUT type="checkbox" name="id" value="${book.id}">
                        </TD>
                        <TD><A href="edit.html?id=${book.id}">${book.code}</A></TD>
                        <TD>${book.title}</TD>
                        <TD>${book.author}</TD>
                        <TD>${book.year}</TD>
                        <TD>${book.amountPages}</TD>
                        <TD>${book.amountLinks}</TD>
                        <TD>${book.citationIndex}</TD>
                    </TR>
                </c:forEach>
            </table>

            <p>
                <a href="edit.html"> Добавить </a>
                <button type="submit"> Удалить </button>
            </p>

            <P>
                Самые лучшие авторы: 
                <c:forEach var="author" items="${bestAuthors}" varStatus="s">
                    ${author}<c:if test="${!s.last}">, </c:if>
                </c:forEach>
            </P>
        </form>
    </body>
</html>