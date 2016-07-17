<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Ветеренарная клиника</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/bootstrap/css/bootstrap.min.css"/>">     <script src="<c:url value="/bootstrap/js/jquery.min.js"/>"></script>     <script src="<c:url value="/bootstrap/js/bootstrap.min.js"/>"></script>
</head>
<body>

<div class="container">
    <h2>
        <div style="display: inline-block;">
            <a style="text-decoration: none; color:inherit;" href="<c:url value="/users.do"/>">Ветеренарная клиника</a>
        </div>
        <div style="width: 100%; display: inline-block; text-align: right;">
            <a href="<c:url value="/login.do?logout"/>" class="btn btn-default" role="button">Выйти</a>
        </div>
    </h2>
    <form role="form" action="<c:url value="/users.do"/>" method="get">
        <div class="form-group">
            <div style="display: inline-block;">
                <input type="text" class="form-control" name="key" placeholder="Поиск:">
            </div>
            <div style="display: inline-block;">
                <button type="submit" class="btn btn-default">Искать</button>
            </div>
        </div>
    </form>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Логин</th>
            <th>Фамилия</th>
            <th>Питомцы</th>
            <th>Email</th>
            <th>Телефон</th>
            <th>Роль</th>
            <th>&nbsp;</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user" varStatus="status">
            <tr valign="top">
            <tr>
            <tr>
                <td><a style="text-decoration: none; color:inherit;" href="<c:url value="/user.do?id=${user.id}"/>"><c:out value="${user.username}"/></a></td>
                <td><c:out value="${user.fullname}"/></td>
                <td>
                    <c:forEach items="${user.pets}" var="pet" varStatus="status">
                        <c:out value="${pet.nick}"/>&nbsp;(<c:out value="${pet.type.name}"/>)<br/>
                    </c:forEach>
                </td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.phone}"/></td>
                <td><c:out value="${user.role.name}"/></td>
                <td>
                    <a style="text-decoration: none; color:inherit;" href="<c:url value="/users/edit.do?id=${user.id}"/>"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>&nbsp;
                    <a style="text-decoration: none; color:inherit;" href="<c:url value="/users/delete.do?id=${user.id}"/>"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>
        <span style="width: 100%; text-align: right; display: inline-block;">
            <a href="<c:url value="/users/add.do"/>" class="btn btn-default" role="button">Добавить клиента</a>&nbsp;
            <a href="<c:url value="/roles.do"/>" class="btn btn-default" role="button">Добавить роль</a>
            <a href="<c:url value="/pets/types.do"/>" class="btn btn-default" role="button">Добавить тип питомца</a>
        </span>
    </p>
</div>
</body>
</html>