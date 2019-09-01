<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добавляем нового пользователя</title>
</head>
<body>
<hr>
<p align="center">Авторизуемся!</p>
<hr/>
<table cellspacing="5" border="0" align="center">
    <form action="/Authorization" method="post">
        <tr>
            <td>
                Login:
            </td>
            <td>
                <input type="text" name="login"/><br>
            </td>
        </tr>
        <tr>
            <td>
                Password:
            </td>
            <td>
                <input type="password" name="password"/><br>
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <input type="submit" value="Вход" width="10"/>
            </td>
        </tr>
    </form>
</table>
</body>
</html>