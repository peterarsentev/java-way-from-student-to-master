<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Ветеренарная клиника</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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
    <form role="form" action="<c:url value="/pets/add.do"/>" method="post" style="width: 50%;">
        <input type="hidden" name="owner.id" value="${user_id}">
        <div class="form-group">
            <label for="email">Кличка:</label>
            <input type="text" class="form-control" name="nick" placeholder="Кличка">
        </div>
        <div class="form-group">
            <label for="email">Тип:</label>
            <select class="form-control" name="type.id" placeholder="Enter email">
                <c:forEach items="${types}" var="type" varStatus="status">
                    <option value="${type.id}"><c:out value="${type.name}"/> </option>
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