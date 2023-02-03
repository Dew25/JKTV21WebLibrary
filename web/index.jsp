
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1>Добро пожаловть в нашу библиотеку</h1>
        
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
    
