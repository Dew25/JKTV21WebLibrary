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
        <p><a href="newBook">Добавить книгу</a></p>
        <p><a href="newAuthor">Добавить автора</a></p>
        <p><a href="listBooks">Список книг</a></p>
        <p><a href="newReader">Добавить читателя</a></p>
        <p><a href="listReaders">Список читателей</a></p>
        <p><a href="takeOnBook">Выдать книгу</a></p>
        <p><a href="listTakedBooks">Список выданных книг</a></p>
        
    </body>
</html>
