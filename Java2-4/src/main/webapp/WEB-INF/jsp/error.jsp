<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isErrorPage="true" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ошибка!</title>
    </head>
    <body>
        <c:set var="status" value="${requestScope['jakarta.servlet.error.status_code']}" />
        <c:set var="message" value="${requestScope['jakarta.servlet.error.message']}" />

        <h2>Ошибка ${status}</h2>
        
        <c:choose>
            <c:when test="${status >= 400 and status < 500}">
                <p>Запрос некорректен или страница не найдена.</p>
                <p>Сообщение: ${message}</p>
            </c:when>

            <c:otherwise>
                <p>Произошла внутренняя ошибка. Попробуйте позже.</p>
            </c:otherwise>
        </c:choose>
    </body>
</html>
