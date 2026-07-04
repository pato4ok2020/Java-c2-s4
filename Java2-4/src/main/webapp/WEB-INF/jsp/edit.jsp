<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:choose>
    <c:when test="${not empty book}">
        <c:set var="code" value="${book.code}"/>
        <c:set var="title" value="${book.title}"/>
        <c:set var="author" value="${book.author}"/>
        <c:set var="year" value="${book.year}"/>
        <c:set var="amountPages" value="${book.amountPages}"/>
        <c:set var="amountLinks" value="${book.amountLinks}"/>
    </c:when>
    <c:otherwise>
        <c:set var="code" value=""/>
        <c:set var="title" value=""/>
        <c:set var="author" value=""/>
        <c:set var="year" value="2026"/>
        <c:set var="amountPages" value="1"/>
        <c:set var="amountLinks" value="0"/>
    </c:otherwise>
</c:choose>

<HTML>
    <HEAD>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <TITLE> Библиотека: редактирование/добавление </TITLE>
    </HEAD>
    <BODY>
        <FORM action="save.html" method="post">
            <c:if test="${not empty book}">
                <INPUT type="hidden" name="id" value="${book.id}">
            </c:if>

            <P> Шифр: </P>
            <INPUT type="text" name="code" value="${code}">

            <P> Автор: </P>
            <INPUT type="text" name="author" value="${author}">

            <P> Название: </P>
            <INPUT type="text" name="title" value="${title}">

            <P> Год издания: </P>
            <INPUT type="number" name="year" value="${year}">

            <P> Кол-во страниц: </P>
            <INPUT type="number" name="amount-pages" value="${amountPages}">

            <P> Кол-во ссылок: </P>
            <INPUT type="number" name="amount-links" value="${amountLinks}"><br>

            <BUTTON type="submit">Сохранить</BUTTON>
            <A href="index.html">Назад</A>
        </FORM>
   </BODY>
</HTML>