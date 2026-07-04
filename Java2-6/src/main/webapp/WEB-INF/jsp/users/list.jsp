<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Факультет: пользователи </title>
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
        <a href="${pageContext.request.contextPath}/">На главную</a><br>

        <form action="delete.html" method="post">
            <table>
                <tr>
                    <th>&nbsp;</th>
                    <th><a href="index.html?sort=login">Логин</a></th>
                    <th><a href="index.html?sort=password">Пароль</a></th>
                    <th><a href="index.html?sort=role">Роль</a></th>
                </tr>

                <c:forEach var="user" items="${users}">
                    <TR>
                        <TD><INPUT type="checkbox" name="id" value="${user.id}"></TD>
                        <TD><A href="edit.html?id=${user.id}">${user.login}</A></TD>
                        <TD>${user.password}</TD>
                        <TD>${user.role}</TD>
                    </TR>
                </c:forEach>
            </table>

            <p>
                <a href="edit.html"> Добавить </a>
                <button type="submit"> Удалить </button>
            </p>
        </form>
    </body>
</html>