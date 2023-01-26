<%-- 
    Document   : listBooks
    Created on : Jan 26, 2023, 3:07:25 PM
    Author     : Melnikov
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Книги в библиотеке</title>
    </head>
    <body>
        <h1>Список книг</h1>
        <ol>
            <c:forEach var="book" items="${listBooks}">
                <li>
                    ${book.title}<br>
                    Авторы книги:<br>
                    <c:forEach var="author" items="${book.authors}">
                        ${author.firstname} ${author.lastname}
                    </c:forEach>
                </li>
            </c:forEach>
        </ol>
    </body>
</html>
