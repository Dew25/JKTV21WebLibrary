
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="w-100 text-center my-5">Список книг</h1>
    <div class="row d-flex justify-content-center">
        <div class="card m-2 border-0" style="width: 35rem;">
            <form action="createHistory" method="POST">
                <div class="mb-3 row">
                    <label for="bookSelect" class="col-sm-4 col-form-label">Список книг</label>
                    <div class="col-sm-8">
                        <select class="form-select" name="bookId" id="bookSelect">
                            <c:forEach var="book" items="${listBooks}">
                                <option value="${book.id}">
                                    ${book.title} 
                                    <c:forEach var="author" items="${book.authors}">
                                        ${author.firstname} ${author.lastname}
                                    </c:forEach>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="mb-3 row">
                    <div class="col-sm-12 d-flex justify-content-end">
                        <input class="btn btn-primary" type="submit" value="Читать книгу">
                    </div>
                </div>
            </form>
        </div>
    </div>
    