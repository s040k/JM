<%--
  Created by IntelliJ IDEA.
  User: MarKorvnikov
  Date: 01.09.2019
  Time: 2:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Выход</title>
</head>
<body>
<hr>
<p align="center">Вы действительно хотите выйти?</p>
<hr/>

<center>
    <form action="/logOut" method="post">
        <input type="submit" value="Выйти"/>
    </form>
    <form action="/login" method="get">
        <input type="submit" value="Отмена"/>
    </form>
</center>
</body>
</html>
