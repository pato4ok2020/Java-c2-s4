<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:choose>
    <c:when test="${not empty editUser}">
        <c:set var="login" value="${editUser.login}"/>
        <c:set var="password" value="${editUser.password}"/>
        <c:set var="role" value="${editUser.role}"/>
    </c:when>
    <c:otherwise>
        <c:set var="login" value=""/>
        <c:set var="password" value=""/>
        <c:set var="role" value=""/>
    </c:otherwise>
</c:choose>

<HTML>
    <HEAD>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <TITLE>Факультет: пользователи: редактирование/добавление</TITLE>
    </HEAD>

    <BODY>
        <a href="${pageContext.request.contextPath}/">На главную</a><br>

        <FORM action="save.html" method="post">
            <c:if test="${not empty editUser}">
                <INPUT type="hidden" name="id" value="${editUser.id}">
            </c:if>

            <P>Логин:</P>
            <INPUT type="text" name="login" value="${login}">

            <P>Пароль:</P>
            <INPUT type="text" name="password" value="${password}">

            <P>Роль:</P>
            <SELECT name="role">
                <OPTION value="common" <c:if test="${role == 'common'}">selected</c:if>>common</OPTION>
                <OPTION value="dean" <c:if test="${role == 'dean'}">selected</c:if>>dean</OPTION>
                <OPTION value="admin" <c:if test="${role == 'admin'}">selected</c:if>>admin</OPTION>
            </SELECT>

            <BUTTON type="submit">Сохранить</BUTTON>
            <A href="index.html">Назад</A>
        </FORM>
    </BODY>
</HTML>