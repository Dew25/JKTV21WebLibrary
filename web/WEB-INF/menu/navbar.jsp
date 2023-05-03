
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="USER" scope="page" value="false" />
<c:set var="MANAGER" scope="page" value="false" />
<c:set var="ADMINISTRATOR" scope="page" value="false" />
<c:forEach var="role" items="${authUser.roles}">
    <c:if test="${role eq 'ADMINISTRATOR'}">
        <c:set var="ADMINISTRATOR" value="true" scope="page" />
    </c:if>
    <c:if test="${role eq 'MANAGER'}">
        <c:set var="MANAGER" value="true" scope="page" />
    </c:if>
    <c:if test="${role eq 'USER'}">
        <c:set var="USER" value="true" scope="page" />
    </c:if>
    
</c:forEach>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="index">JKTV21Library</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Книги
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <c:if test="${MANAGER}">
                <li><a class="dropdown-item" href="newBook">Добавить книгу</a></li>
                <li><a class="dropdown-item" href="listTakedBooks">Список выданных книг</a></li>
              </c:if>
            <li><a class="dropdown-item" href="listBooks">Список книг</a></li>
            <c:if test="${USER}">
              <li><a class="dropdown-item" href="takeOnBook">Выдать книгу</a></li>
              <li><a class="dropdown-item" href="formReturnBook">Вернуть книгу</a></li>
            </c:if>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Авторы
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <c:if test="${MANAGER}">
                <li><a class="dropdown-item" href="newAuthor">Добавить автора</a></li>
            </c:if>
            <li><a class="dropdown-item" href="listAuthors">Список авторов</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Читатели
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="newReader">Добавить читателя</a></li>
            <c:if test="${ADMINISTRATOR}">
                <li><a class="dropdown-item" href="listReaders">Список читателей</a></li>
            </c:if>
          </ul>
        </li>
        <c:if test="${ADMINISTRATOR}">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Администратор
              </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="changeRole">Изменить роль</a></li>
              </ul>
            </li>
        </c:if>
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="about">О нас</a>
        </li>
      </ul>
      <c:if test="${USER}">
        <span class="text-light mx-3">Логин: ${authUser.login}</span>
      </c:if>
      <ul class="navbar-nav mb-2 mb-lg-0">
        <li class="nav-item">
            <c:if test="${USER eq false}">
                <a class="nav-link " aria-current="page" href="loginForm">Войти</a>
            </c:if>
        </li>
        <li class="nav-item">
            <c:if test="${USER}">
                <a class="nav-link" aria-current="page" href="logout">Выйти</a>
            </c:if>
        </li>
      </ul>
    </div>
  </div>
</nav>
