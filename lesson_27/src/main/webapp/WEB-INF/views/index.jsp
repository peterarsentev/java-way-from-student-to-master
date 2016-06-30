<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
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