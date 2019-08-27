
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<hr>
<p align="center">Удалить пользователя <c:out value="${param.login}"/>?</p>
<hr/>
<br>
<center>
<form action="/users/delete" method="post">
    <input type="hidden" name="id" value="${param.id}"/>
    <input type="hidden" name="action" value="delete"/>
    <input type="submit" value="Ок"/>
</form>
<form action="/users" method="get">
    <input type="submit" value="Отмена"/>
</form>
</center>
</body>
</html>
