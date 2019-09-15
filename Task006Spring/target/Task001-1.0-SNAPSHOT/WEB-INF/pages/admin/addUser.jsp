<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

        Name:<input type="text" name="name"/>
        Login:<input type="text" name="login"/>
        Password:<input type="password" name="password"/>
        Role:
        <label for="role1">User</label>
        <input type="radio" id="role1" name="role" value="ROLE_USER" checked/>
        <label for="role2">Admin</label>
        <input type="radio" id="role2" name="role" value="ROLE_ADMIN"/><br>
        <br>
        <input type="submit" value="Добавить"/>

    </form>

    <form action="/" method="get">
        <input type="submit" value="Отмена"/>

    </form>
</center>
</body>
</html>