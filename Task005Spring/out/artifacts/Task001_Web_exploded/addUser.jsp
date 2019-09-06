
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
    <title>Добавляем нового  пользователя</title>
</head>
<body>

<hr>
<p align="center">Добавляем нового пользователя</p>
<hr/>

<br>
<center>
<form action="/users/add" method="post">

    Name:<input type="text" name="name"/>
    Login:<input type="text" name="login"/>
    Password:<input type="password" name="password"/><br>
    <br>
    <input type="submit" value="Добавить"/>
</form>

<form action="/users" method="get">
    <input type="submit" value="Отмена"/>

</form>
</center>
</body>
</html>