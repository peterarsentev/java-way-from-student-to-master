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
    <h2>Ветеренарная клиника</h2>
    <p>
        <span style="width: 100%; text-align: right; display: inline-block;"><button type="submit" class="btn btn-success">Добавить</button></span>
        <span>Клиенты</span>
    </p>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Email</th>
            <th>Телефон</th>
            <th>Питомцы</th>
            <th>&nbsp;</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>John</td>
            <td>Doe</td>
            <td>john@example.com</td>
            <td>123</td>
            <td></td>
            <td><span class="glyphicon glyphicon-edit" aria-hidden="true"></span>&nbsp;
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span></td>
        </tr>
        </tbody>
    </table>
    <form role="form">
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" placeholder="Enter email">
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" placeholder="Enter password">
        </div>
        <div class="checkbox">
            <label><input type="checkbox"> Remember me</label>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>

</div>

Welcome, ${user.name}<br/>
<form action="${pageContext.servletContext.contextPath}/items.do" method="post">
    name : <input type="text" name="name"><br/>
    desc : <input type="text" name="desc"><br/>
    handler :
    <select name="handler.name">
        <option value="Petr">Petr</option>
        <option value="Oleg">Oleg</option>
    </select>
    <input type="submit"><br/>
</form>
<table border="1">
    <tr>
        <td>Имя</td>
        <td>Описание</td>
        <td>Отвественный</td>
    </tr>
    <c:forEach items="${items}" var="item" varStatus="status">
        <tr valign="top">
            <td>${item.name}</td>
            <td>${item.desc}</td>
            <td>${item.handler}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>