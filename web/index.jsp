
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="w-100 text-center">Добро пожаловать в нашу библиотеку</h1>
        
        <div class="row d-flex justify-content-center ">
            <c:forEach var="book" items="${listBooks}">
                <div class="card m-2" style="width: 15rem;">
                    <img src="..." class="card-img-top" alt="...">
                    <div class="card-body">
                      <h5 class="card-title">${book.title}</h5>
                      <p class="card-text">
                          Авторы книги:<br>
                            <c:forEach var="author" items="${book.authors}">
                                ${author.firstname} ${author.lastname}
                            </c:forEach>
                      
                      </p>
                      <a href="book?bookId=${book.id}" class="btn btn-primary">Посмотреть книгу</a>
                    </div>
                </div>
            </c:forEach>
        </div>
