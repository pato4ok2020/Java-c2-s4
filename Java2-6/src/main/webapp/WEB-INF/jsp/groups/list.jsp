<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Факультет: группы</title>

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
                    <th><a href="index.html?sort=name">Название</a></th>
                    <th><a href="index.html?sort=amount_students">Количество студентов</a></th>
                    <th><a href="index.html?sort=specialization">Специальность</a></th>
                    <th><a href="index.html?sort=course">Курс</a></th>
                    <th><a href="index.html?sort=study_form">Форма обучения</a></th>
                    <th>&nbsp;</th>
                </tr>

                <c:forEach var="group" items="${groups}">
                    <tr>
                        <td>
                            <input type="checkbox" name="id" value="${group.id}">
                        </td>
                        <td><a href="edit.html?id=${group.id}">${group.name}</a></td>
                        <td>${group.amountStudents}</td>
                        <td>${group.specialization}</td>
                        <td>${group.course}</td>
                        <td>${group.studyForm}</td>
                        <td><a href="${pageContext.request.contextPath}/students/index.html?id=${group.id}">Список студентов</a></td>
                    </tr>
                </c:forEach>
            </table>

            <p>
                <a href="edit.html">Добавить</a>
                <button type="submit">Удалить</button>
            </p>
        </form>
    </body>
</html>