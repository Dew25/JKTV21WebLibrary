<%-- 
    Document   : takeOnBook
    Created on : Jan 30, 2023, 10:24:54 AM
    Author     : Melnikov
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Выдача книги читателю</title>
    </head>
    <body>
        <h1>Выдать книгу читателю</h1>
        <form action="createHistory" method="POST">
        <p>Список читателей</p>
        <p>
        <select name="readerId">
            <c:forEach var="reader" items="${listReaders}">
                <option value="${reader.id}">${reader.firstname} ${reader.lastname}</option>
            </c:forEach>
        </select>
        </p>
        <p>Список книг</p>
        <p>
        <select name="bookId">
            <c:forEach var="book" items="${listBooks}">
                <option value="${book.id}">
                    ${book.title} 
                    <c:forEach var="author" items="${book.authors}">
                        ${author.firstname} ${author.lastname}
                    </c:forEach>
                </option>
            </c:forEach>
        </select>
        </p>
        <input type="submit" value="Выдать книгу">
        </form>
    </body>
</html>
