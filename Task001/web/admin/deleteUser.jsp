
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Удалить пользователя</title>
</head>
<body>
<hr>
<p align="center">Удалить пользователя <c:out value="${requestScope.login}"/>?</p>
<hr/>
<br>
<center>
<form action="/admin/users/delete" method="post">
    <input type="hidden" name="id" value="${requestScope.id}"/>
    <input type="hidden" name="action" value="delete"/>
    <input type="submit" value="Ок"/>
</form>
<form action="/login" method="get">
    <input type="submit" value="Отмена"/>
</form>
</center>
</body>
</html>
