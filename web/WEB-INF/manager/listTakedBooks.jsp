
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="w-100 text-center my-5">Выданные книги:</h1>
        <div class="row d-flex justify-content-center ">
            <div class="card m-2 border-0" style="width: 40rem;">
                <table class="table">
                    <thead>
                      <tr>
                        <th class="col col-sm-1">Id</th>
                        <th class="col col-lg-8">Название книги</th>
                      </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="history" items="${listTakedBooks}" varStatus="status">
                            <tr>
                              <th scope="row">${status.index+1}</th>
                              <td> ${history.book.title}
                                   <c:forEach var="author" items="${history.book.authors}">
                                        ${author.firstname} ${author.lastname}
                                    </c:forEach>
                              </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                  </table>
            </div>
        </div>
       
    