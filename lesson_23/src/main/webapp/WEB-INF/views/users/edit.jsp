<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Ветеренарная клиника</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/bootstrap/css/bootstrap.min.css"/>">     <script src="<c:url value="/bootstrap/js/jquery.min.js"/>"></script>     <script src="<c:url value="/bootstrap/js/bootstrap.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/bootstrap/css/style.css"/>">
</head>
<body>

<div class="container">
    <h2>
        <div style="display: inline-block;">
            Ветеренарная клиника
        </div>
        <div style="width: 100%; display: inline-block; text-align: right;">
            <a href="<c:url value="/login.do?logout"/>" class="btn btn-default" role="button">Выйти</a>
        </div>
    </h2>
    <form role="form" action="<c:url value="/users/edit.do"/>" method="post" style="width: 50%;">
        <input type="hidden" name="id" value="${user.id}">
        <div class="form-group">
            <label for="email">Логин:</label>
            <input type="text" class="form-control" name="username" value="${user.username}" placeholder="Логин">
        </div>
        <div class="form-group">
            <label for="email">ФИО:</label>
            <input type="text" class="form-control" name="fullname" value="${user.fullname}" placeholder="ФИО">
        </div>
        <div class="form-group">
            <label for="email">Телефон:</label>
            <input type="text" class="form-control" name="phone" value="${user.phone}" placeholder="Телефон">
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" name="email" value="${user.email}" placeholder="Enter email">
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" name="password" value="${user.password}" placeholder="Enter password">
        </div>
        <div class="form-group">
            <ul class="list-group">
                <li class="list-group-item">
                    Активный
                    <div class="material-switch pull-right">
                        <input id="enabled" name="enabled" ${user.enabled ? 'checked' : ''} type="checkbox"/>
                        <label for="enabled" class="label-default"></label>
                    </div>
                </li>
            </ul>
        </div>
        <div class="form-group">
            <label for="email">Роль:</label>
            <select class="form-control" name="role.id" placeholder="Enter email">
                <c:forEach items="${roles}" var="role" varStatus="status">
                    <option ${user.role.id == role.id ? 'selected' : ''} value="${role.id}"><c:out value="${role.name}"/> </option>
                </c:forEach>
            </select>
        </div>
         <span style="width: 100%; display: inline-block;">
            <button type="submit" class="btn btn-default">Сохранить</button>
            <a href="<c:url value="/users.do"/>" class="btn btn-default" role="button">Назад</a>
        </span>
    </form>
</div>
</body>
</html>