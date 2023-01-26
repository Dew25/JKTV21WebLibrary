<%-- 
    Document   : createAutor
    Created on : Jan 26, 2023, 2:11:51 PM
    Author     : Melnikov
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Создание новой книги</title>
    </head>
    <body>
        <h1>Новая книга</h1>
        <form action="createBook" method="POST">
            Название: <input type="text" name="title" value=""><br>
            Авторы: 
            <select name="authors" multiple="true">
                <c:forEach var="author" items="${listAuthors}">
                    <option value="${author.id}">${author.firstname} ${author.lastname}</option>
                </c:forEach>
            </select>
            <br>
            <input type="submit" value="Добавить">
        </form>
    </body>
</html>
