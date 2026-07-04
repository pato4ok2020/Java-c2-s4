<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Факультет: студенты</title>

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
                    <th><a href="index.html?sort=group_id${not empty param.id ? '&id='.concat(param.id) : ''}">Группа ID</a></th>
                    <th><a href="index.html?sort=last_name${not empty param.id ? '&id='.concat(param.id) : ''}">Фамилия</a></th>
                    <th><a href="index.html?sort=first_name${not empty param.id ? '&id='.concat(param.id) : ''}">Имя</a></th>
                    <th><a href="index.html?sort=middle_name${not empty param.id ? '&id='.concat(param.id) : ''}">Отчество</a></th>
                    <th><a href="index.html?sort=sex${not empty param.id ? '&id='.concat(param.id) : ''}">Пол</a></th>
                    <th><a href="index.html?sort=birthday${not empty param.id ? '&id='.concat(param.id) : ''}">Дата рождения</a></th>
                    <th><a href="index.html?sort=funding_type${not empty param.id ? '&id='.concat(param.id) : ''}">Тип обучения</a></th>
                    <th><a href="index.html?sort=failed_exams${not empty param.id ? '&id='.concat(param.id) : ''}">Несданные экзамены</a></th>
                </tr>

                <c:forEach var="student" items="${students}">
                    <tr>
                        <td><input type="checkbox" name="id" value="${student.id}"></td>
                        <td>${student.groupId}</td>
                        <td><a href="edit.html?id=${student.id}">${student.lastName}</a></td>
                        <td>${student.firstName}</td>
                        <td>${student.middleName}</td>
                        <td>${student.sex}</td>
                        <td>${student.birthday}</td>
                        <td>${student.fundingType}</td>
                        <td>${student.failedExams}</td>
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