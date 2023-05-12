
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <h1 class="w-100 text-center my-5">Новая книга</h1>
        <div class="row d-flex justify-content-center ">
        <a class="w-100 text-center my-2" href="uploadForm">Загрузить обложку</a>
            <div class="card m-2 border-0" style="width: 35rem;">
                <form action="createBook" method="POST">
                    <div class="mb-3 row">
                        <label for="inputTitle" class="col-sm-3 col-form-label">Название:</label>
                        <div class="col-sm-9">
                          <input type="text" class="w-100" id="inputTitle" name="title" value="">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="selectAuthors" class="col-sm-3 col-form-label">Авторы:</label>
                        <div class="col-sm-9">
                            <select class="form-select" name="authors" id="selectAuthors" multiple="true">
                                <c:forEach var="author" items="${listAuthors}">
                                    <option value="${author.id}">${author.firstname} ${author.lastname}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="selectCover" class="col-sm-4 col-form-label">Обложки</label>
                        <div class="col-sm-8">
                            <select class="form-select" name="coverId" rows="5" cols="20" id="selectCover">
                                <c:forEach var="cover" items="${listCovers}">
                                    <option value="${cover.id}">${cover.description}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <div class="col-sm-12 d-flex justify-content-end">
                            <input class="btn btn-primary" type="submit" value="Добавить">
                        </div>
                    </div>
                </form>
            </div>
    
        </div>