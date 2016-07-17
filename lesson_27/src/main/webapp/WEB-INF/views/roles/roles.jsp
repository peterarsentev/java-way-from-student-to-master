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
            Ветеренарная клиника
        </div>
        <div style="width: 100%; display: inline-block; text-align: right;">
            <a href="<c:url value="/login.do?logout"/>" class="btn btn-default" role="button">Выйти</a>
        </div>
    </h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Роль</th>
            <th>&nbsp;</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${roles}" var="role" varStatus="status">
            <tr valign="top">
            <tr>
            <tr>
                <td><c:out value="${role.name}"/></td>
                <td></td>
                <td>
                    <a style="text-decoration: none; color:inherit;" href="<c:url value="/users/add.do"/>"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>&nbsp;
                    <a style="text-decoration: none; color:inherit;" href="<c:url value="/users/add.do"/>"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form role="form" action="<c:url value="/roles/add.do"/>" method="post" style="width: 50%;">
        <div class="form-group">
            <label for="email">Имя:</label>
            <input type="text" class="form-control" name="name" placeholder="Имя">
        </div>
        <span style="width: 100%; display: inline-block;">
            <button type="submit" class="btn btn-default">Сохранить</button>
            <a href="<c:url value="/users.do"/>" class="btn btn-default" role="button">Назад</a>
        </span>
    </form>
</div>
</body>
</html>