<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/users" method="POST">
    Имя : <input type="text" name="name"><br/>
    Логин : <input type="text" name="login"><br/>
    Дата рождения : <input type="date" name="create"><br/>
    <input type="submit"><br/>
</form>
<table border="1">
    <tr>
        <td>Имя</td>
        <td>Логин</td>
        <td>Дата рождения</td>
    </tr>
    <c:forEach items="${users}" var="user" varStatus="status">
        <tr valign="top">
            <td>${user.login}</td>
            <td>${user.name}</td>
            <td><fmt:formatDate type="date" value="${user.created.time}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>