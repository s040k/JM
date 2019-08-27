
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Результат</title>
</head>
<body>

<hr>
<p align="center">${requestScope.resultMessage}</p>
<hr>

<center>
    <form action="/users" method="get">
        <input type="submit" value="Вернуться на главную страницу" align="center"/>
    </form>
</center>

</body>
</html>
