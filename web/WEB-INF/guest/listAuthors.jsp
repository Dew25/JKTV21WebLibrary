<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1 class="w-100 text-center my-5">Список авторов</h1>
        <div class="row d-flex justify-content-center ">
            <div class="card m-2 border-0" style="width: 40rem;">
                <table class="table">
                    <thead>
                      <tr>
                        <th class="col col-sm-1">№</th>
                        <th class="col col-lg-8">Автор</th>
                        <th class="col col-lg-3">Рождение</th>
                      </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="author" items="${listAuthors}" varStatus="status">
                            <tr>
                              <th scope="row">${status.index+1}</th>
                              <td>${author.firstname} ${author.lastname}</td>
                              <td>${author.strBirthday}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                  </table>
            </div>
        </div>
