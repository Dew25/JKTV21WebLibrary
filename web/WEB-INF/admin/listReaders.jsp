
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <h1 class="w-100 text-center my-5">Список читателей</h1>
        <div class="row d-flex justify-content-center ">
            <div class="card m-2 border-0" style="width: 40rem;">
                <table class="table">
                    <thead>
                      <tr>
                        <th class="col col-sm-1">№</th>
                        <th class="col col-lg-8">Читатель</th>
                        <th class="col col-lg-3">Телефон</th>
                      </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="reader" items="${listReaders}" varStatus="status">
                            <tr>
                              <th scope="row">${status.index+1}</th>
                              <td>${reader.firstname} ${reader.lastname}</td>
                              <td>${reader.phone}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                  </table>
            </div>
        </div>
        
    