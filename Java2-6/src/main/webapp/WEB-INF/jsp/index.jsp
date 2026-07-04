<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Факультет: главная</title>
    </head>
    <body>
        <h1>Факультет: главная</h1>

        <c:choose>
            <c:when test="${not empty user}">
                <p>Вы: ${user.login}</p>
                <a href="logout.html">Выйти</a>
            </c:when>

            <c:otherwise>
                <a href="login.html">Войти</a>
            </c:otherwise>
        </c:choose>

        <nav>
            <a href="groups/index.html">Группы</a><br>
            <a href="students/index.html">Студенты</a><br>
            <a href="users/index.html">Пользователи</a><br>
        </nav>
    </body>
</html>