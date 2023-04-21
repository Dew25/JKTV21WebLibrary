
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="w-100 text-center my-5">Выберите возвращаемую книгу:</h1>
        <div class="row d-flex justify-content-center ">
           <div class=" m-2" style="width: 30rem;">
               <c:forEach var="history" items="${listTakedBooks}" varStatus="status">
                     <a href="returnBook?historyId=${history.id}">
                         ${status.index + 1}. ${history.book.title}. 
                             Авторы книги: 
                        <c:forEach var="author" items="${history.book.authors}">
                            ${author.firstname} ${author.lastname}
                        </c:forEach>
                     </a><br>
            </c:forEach>
          </div>
        </div>
       
    