<%-- 
    Document   : listReaders
    Created on : Jan 30, 2023, 9:22:51 AM
    Author     : Melnikov
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список читателей</title>
    </head>
    <body>
        <h1>Список читателей</h1>
        <ol>
            <c:forEach var="reader" items="${listReaders}">
                <li>
                    ${reader.firstname} ${reader.lastname}. Телефон: ${reader.phone}
                </li>
            </c:forEach>
        </ol>
    </body>
</html>
