
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Выданные книги:</h1>
        <ol>
            <c:forEach var="history" items="${listTakedBooks}">
                <li>
                    ${history.book.title}<br>
                    Авторы книги:<br>
                    <c:forEach var="author" items="${history.book.authors}">
                        ${author.firstname} ${author.lastname}
                    </c:forEach>
                </li>
            </c:forEach>
        </ol>
    