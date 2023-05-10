<%-- 
    Document   : statisticForm
    Created on : May 10, 2023, 1:43:40 PM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h5 class="w-100 d-flex justify-content-center mt-5">Рейтинг за ${period}</h5>
<div class="w-100 d-flex justify-content-center mt-4">
    <div class="card border-0" style="width: 24rem;">
        <table class="table">
          <thead>
            <tr>
              <th scope="col">№</th>
              <th scope="col">Название книги</th>
              <th scope="col">Количество<br>прочитанных</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="entry" items="${mapBooksRange}" varStatus="status">
            <tr>
              <th scope="row">${status.index + 1}</th>
              <td>${entry.key.title}</td>
              <td>${entry.value}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
