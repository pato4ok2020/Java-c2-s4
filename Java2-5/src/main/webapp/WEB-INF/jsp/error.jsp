<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isErrorPage="true" %>
<% Integer status = (Integer) request.getAttribute("jakarta.servlet.error.status_code"); %>
<% String message = (String) request.getAttribute("jakarta.servlet.error.message"); %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Ошибка! </title>
    </head>
    <body>
        <h2> Ошибка <%= status %> </h2>
        <% if (status != null && status >= 400 && status < 500) { %>
            <p>Запрос некорректен или страница не найдена.</p>
            <p> Сообщение: <%= message %> </p>
        <% } else { %>
            <p>Произошла внутренняя ошибка. Попробуйте позже.</p>
        <% } %>
    </body>
</html>