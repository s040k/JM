<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добавляем нового пользователя</title>
</head>
<body>

<hr>
<p align="center">Добавляем нового пользователя</p>
<hr/>

<br>
<center>
    <form action="/admin/users/add" method="post">

        Имя:<input type="text" name="name"/>
        Логин:<input type="text" name="login"/>
        Пароль:<input type="password" name="password"/><br>
        Права:
        <c:forEach items="${requestScope.simpleRoles}" var="role">
            <label for="${role.ordinal()}">${role.getPresentationName()}</label>
            <input type="checkbox" id="${role.ordinal()}" name="checkBoxParameter ${role.ordinal()}" value="${role.name()}">
        </c:forEach>
        <br>
        <br>
        <input type="submit" value="Добавить"/>

    </form>

    <form action="/" method="get">
        <input type="submit" value="Отмена"/>

    </form>
</center>
</body>
</html>