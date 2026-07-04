<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:choose>
    <c:when test="${not empty group}">
        <c:set var="name" value="${group.name}"/>
        <c:set var="specialization" value="${group.specialization}"/>
        <c:set var="course" value="${group.course}"/>
        <c:set var="studyForm" value="${group.studyForm}"/>
    </c:when>

    <c:otherwise>
        <c:set var="name" value=""/>
        <c:set var="specialization" value=""/>
        <c:set var="course" value=""/>
        <c:set var="studyForm" value=""/>
    </c:otherwise>
</c:choose>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Факультет: группы: редактирование/добавление</title>
    </head>

    <body>
        <a href="${pageContext.request.contextPath}/">На главную</a><br>

        <form action="save.html" method="post">

            <c:if test="${not empty group}">
                <input type="hidden" name="id" value="${group.id}">
            </c:if>

            <p>Название:</p>
            <input type="text" name="name" value="${name}">

            <p>Специальность:</p>
            <input type="text" name="specialization" value="${specialization}">

            <p>Курс:</p>
            <input type="text" name="course" value="${course}">

            <p>Форма обучения:</p>

            <select name="studyForm">
                <option value="очная"
                    <c:if test="${studyForm == 'очная'}">selected</c:if>>
                    очная
                </option>

                <option value="заочная"
                    <c:if test="${studyForm == 'заочная'}">selected</c:if>>
                    заочная
                </option>
            </select>

            <br><br>

            <button type="submit">Сохранить</button>

            <a href="index.html">Назад</a>
        </form>
    </body>
</html>