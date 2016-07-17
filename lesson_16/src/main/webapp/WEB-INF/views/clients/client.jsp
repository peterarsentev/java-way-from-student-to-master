<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Ветеренарная клиника</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/bootstrap/css/bootstrap.min.css"/>">
    <script src="<c:url value="/bootstrap/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/bootstrap/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript">
        function createMessage() {
            $.ajax('<c:url value="/client/message/add.do"/>', {
                method : 'post',
                data: {text : $('#text').val(), 'owner.id' : '${user.id}'},
                complete: function(data) {
                    loadMessages();
                    $('#text').val('');
                }
            });
        }

        function loadMessages() {
            $.ajax('<c:url value="/client/messages.do"/>', {
                method : 'get',
                success: function(data) {
                    var table = "";
                    var messages = JSON.parse(data);
                    var size = messages.length;
                    for (var i=0;i!=size;++i) {
                        var message = messages[i];
                        var date = new Date(message.created);
                        table += "<li class='media'>";
                        table += "<div class='media-body'>";
                        table += "<div class='text-muted pull-right'>";
                        table += "<small class='text-muted'>"+ date.toISOString() +"</small>";
                        table += "</div>";
                        table += "<strong class='text-success'>"+message.author.fullname+"("+message.author.username+")</strong>";
                        table += "<p>"+message.text+"</p>";
                        table += "</div>";
                        table += "</li>";
                    }
                    $('#messages').html(table);
                }
            });
        }

        $(function() {
            loadMessages();
        });
    </script>
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
        <div class="panel panel-info">
            <div class="panel-heading">
                Сообщения
            </div>
            <div class="panel-body">
                    <textarea class="form-control" id="text" name="text" placeholder="Enter here for tweet..." rows="3"></textarea>
                    <br>
                    <button type="button" onclick="return createMessage();" class="btn btn-default pull-right">Добавить</button>
                <div class="clearfix"></div>
                <hr>
                <ul class="media-list" id="messages">
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>