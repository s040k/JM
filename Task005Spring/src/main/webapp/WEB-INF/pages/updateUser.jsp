
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменяем данные пользователя</title>
</head>
<body>
<hr>
<p align="center">Изменяем данные пользователя <c:out value="${param.login}"/> <br/></p>
<hr/>
<center>
<form action="/users/update" method="post">
    <input type="hidden" name="id" value="${requestScope.id}"/>
        Name:<input type="text" name="name" value="${requestScope.name}"/>
        Login:<input type="text" name="login" value="${requestScope.login}"/>
        Password:<input type="text" name="password" value="${requestScope.password}"/>
        <br>
        <br>
        <input type="submit" value="Изменить"/>
</form>

<form action="/users" method="get">
    <input type="submit" value="Отмена"/>
</form>
</center>
</body>
</html>