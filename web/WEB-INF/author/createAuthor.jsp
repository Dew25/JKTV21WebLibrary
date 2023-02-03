<%-- 
    Document   : createAutor
    Created on : Jan 26, 2023, 2:11:51 PM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Создание нового автора</title>
    </head>
    <body>
        <h1>Новый автор</h1>
        <form action="createAuthor" method="POST">
            Имя: <input type="text" name="firstname" value=""><br>
            Фамилия: <input type="text" name="lastname" value=""><br>
            Год рождения: <input type="text" name="year" value=""><br>
            Месяц рождения: <input type="text" name="month" value=""><br>
            День рождения: <input type="text" name="day" value=""><br>
            <input type="submit" value="Добавить">
        </form>
    </body>
</html>
