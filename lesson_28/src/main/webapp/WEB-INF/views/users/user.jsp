<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <div class="panel panel-info">
        <table class="table table-bordered">
            <tr>
                <td>Имя</td>
                <td>${user.username}</td>
            </tr>
            <tr>
                <td>Логин</td>
                <td>${user.fullname}</td>
            </tr>
            <tr>
                <td>Имя</td>
                <td>${user.fullname}</td>
            </tr>
            <tr>
                <td>Телефон</td>
                <td>${user.phone}</td>
            </tr>
            <tr>
                <td>Email</td>
                <td>${user.email}</td>
            </tr>
            <tr>
                <td>Питомцы</td>
                <td>
                    <c:forEach items="${user.pets}" var="pet" varStatus="status">
                        <c:out value="${pet.nick}"/>&nbsp;(<c:out value="${pet.type.name}"/>)<br/>
                    </c:forEach>
                </td>
            </tr>
        </table>
    </div>
    <div>
        <a href="<c:url value="/pets.do?id=${user.id}"/>" class="btn btn-default" role="button">Добавить питомца</a>
        <a href="<c:url value="/users.do"/>" class="btn btn-default" role="button">Назад</a>
    </div><br/>
    <div>
        <div class="panel panel-info">
            <div class="panel-heading">
                Сообщения
            </div>
            <div class="panel-body">
                <form action="<c:url value="/messages/add.do"/>" method="POST">
                    <input type="hidden" name="owner.id" value="${user.id}">
                    <textarea class="form-control" name="text" placeholder="Enter here for tweet..." rows="3"></textarea>
                    <br>
                    <button type="submit" class="btn btn-default pull-right">Добавить</button>
                </form>
                <div class="clearfix"></div>
                <hr>
                <ul class="media-list">
                    <c:forEach items="${messages}" var="msg" varStatus="status">
                        <li class="media">
                            <div class="media-body">
                                <div class="text-muted pull-right">
                                    <small class="text-muted"><fmt:formatDate type="date" value="${msg.created}"/></small>
                                    <a style="text-decoration: none; color:inherit;" href="<c:url value="/messages/delete.do?userId=${user.id}&messageId=${msg.id}"/>">
                                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                    </a>
                                </div>
                                <strong class="text-success">${msg.author.fullname}&nbsp;(${msg.author.username})</strong>
                                <p>${msg.text}</p>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>