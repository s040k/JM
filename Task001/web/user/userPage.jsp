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
<p align="center">Развлекайся братюнь</p>
<hr/>

<center>
    <form action="${pageContext.request.contextPath}/logOut" method="get">
        <input type="submit" value="Выйти"/>
    </form>
</center>
</body>
</html>
