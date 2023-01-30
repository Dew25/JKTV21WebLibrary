<%-- 
    Document   : createReader
    Created on : Jan 30, 2023, 9:13:26 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Создание читателя</title>
    </head>
    <body>
        <h1>Новый читатель</h1>
        <form action="createReader" method="POST">
            Имя: <input type="text" name="firstname" value=""><br>
            Фамилия: <input type="text" name="lastname" value=""><br>
            Телефон: <input type="text" name="phone" value=""><br>
            <br>
            <input type="submit" value="Добавить">
        </form>
    </body>
</html>
