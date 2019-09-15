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
<c:set var="role" value="${requestScope.role}" scope="page"/>

<center>
    <form action="/admin/users/update" method="post">
        <input type="hidden" name="id" value="${requestScope.id}"/>
        Name:<input type="text" name="name" value="${requestScope.name}"/>
        Login:<input type="text" name="login" value="${requestScope.login}"/>
        Password:<input type="text" name="password" value="${requestScope.password}"/>
        Role:
        <label for="role1">User</label>
        <input type="radio" id="role1" name="role" value="ROLE_USER" <c:out value="${role.equals('ROLE_USER')?'checked':''}"/>>
        <label for="role2">Admin</label>
        <input type="radio" id="role2" name="role" value="ROLE_ADMIN" <c:out value="${role.equals('ROLE_ADMIN')?'checked':''}"/>>
        <br>
        <br>
        <input type="submit" value="Изменить"/>
    </form>

    <form action="/" method="get">
        <input type="submit" value="Отмена"/>
    </form>
</center>
</body>
</html>