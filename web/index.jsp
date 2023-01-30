<%-- 
    Document   : index
    Created on : Jan 26, 2023, 1:56:34 PM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JKTV21WebLibrary</title>
    </head>
    <body>
        <h1>Добро пожаловать в нашу библиотеку!</h1>
        <p>${info}</p>
        <p><a href="newBook">Добавть книгу</a></p>
        <p><a href="newAuthor">Добавть автора</a></p>
        <p><a href="listBooks">Список книг</a></p>
        <p><a href="newReader">Добавть читателя</a></p>
        <p><a href="listReaders">Список читателей</a></p>
    </body>
</html>
