<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:choose>
    <c:when test="${not empty student}">
        <c:set var="groupId" value="${student.groupId}"/>
        <c:set var="lastName" value="${student.lastName}"/>
        <c:set var="firstName" value="${student.firstName}"/>
        <c:set var="middleName" value="${student.middleName}"/>
        <c:set var="sex" value="${student.sex}"/>
        <c:set var="birthday" value="${student.birthday}"/>
        <c:set var="fundingType" value="${student.fundingType}"/>
        <c:set var="failedExams" value="${student.failedExams}"/>
    </c:when>

    <c:otherwise>
        <c:set var="groupId" value=""/>
        <c:set var="lastName" value=""/>
        <c:set var="firstName" value=""/>
        <c:set var="middleName" value=""/>
        <c:set var="sex" value=""/>
        <c:set var="birthday" value=""/>
        <c:set var="fundingType" value=""/>
        <c:set var="failedExams" value=""/>
    </c:otherwise>
</c:choose>

<HTML>
    <HEAD>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <TITLE>Факультет: студенты: редактирование/добавление</TITLE>
    </HEAD>

    <BODY>
        <a href="${pageContext.request.contextPath}/">На главную</a><br>

        <FORM action="save.html" method="post">
            <c:if test="${not empty student}">
                <INPUT type="hidden" name="id" value="${student.id}">
            </c:if>

            <P>Группа ID:</P>
            <INPUT type="text" name="group_id" value="${groupId}">

            <P>Фамилия:</P>
            <INPUT type="text" name="last_name" value="${lastName}">

            <P>Имя:</P>
            <INPUT type="text" name="first_name" value="${firstName}">

            <P>Отчество:</P>
            <INPUT type="text" name="middle_name" value="${middleName}">

            <P>Пол:</P>
            <SELECT name="sex">
                <OPTION value="м" <c:if test="${sex == 'м'}">selected</c:if>>м</OPTION>
                <OPTION value="ж" <c:if test="${sex == 'ж'}">selected</c:if>>ж</OPTION>
            </SELECT>

            <P>Дата рождения:</P>
            <INPUT type="date" name="birthday" value="${birthday}">

            <P>Тип финансирования:</P>
            <SELECT name="fundingType">
                <OPTION value="бюджетник" <c:if test="${fundingType == 'бюджетник'}">selected</c:if>>бюджетник</OPTION>
                <OPTION value="платник" <c:if test="${fundingType == 'платник'}">selected</c:if>>платник</OPTION>
            </SELECT>

            <P>Несданные экзамены:</P>
            <INPUT type="text" name="failedExams" value="${failedExams}">

            <BUTTON type="submit">Сохранить</BUTTON>
            <A href="index.html">Назад</A>
        </FORM>
    </BODY>
</HTML>