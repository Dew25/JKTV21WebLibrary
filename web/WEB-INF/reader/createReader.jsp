
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Новый читатель</h1>
        <form action="createReader" method="POST">
            Имя: <input type="text" name="firstname" value=""><br>
            Фамилия: <input type="text" name="lastname" value=""><br>
            Телефон: <input type="text" name="phone" value=""><br>
            <br>
            <input type="submit" value="Добавить">
        </form>
    
