<%--
  Created by IntelliJ IDEA.
  User: MarKorvnikov
  Date: 01.09.2019
  Time: 1:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Это твоя жизнь</title>
</head>
<body>
<hr>
<p align="center">Личная страница ${requestScope.name}</p>
<hr/>

<table cellspacing="5" border="5" align="center">
    <tr>
        <td>
            Информация о пользователе:<br/>
        </td>
    </tr>
    <tr>
        <td>
            Name: ${requestScope.name}<br/>
            Login: ${requestScope.login} <br/>
            Password: ${requestScope.password} <br/>
        </td>
    </tr>
</table>


<center>
    <form action="${pageContext.request.contextPath}/logout" method="get">
        <input type="submit" value="Выйти"/>
    </form>
</center>
</body>
</html>
