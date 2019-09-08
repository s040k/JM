
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Результат</title>

</head>
<body>

<hr>
<p align="center">${sessionScope.resultMessage}</p>
<hr>

<center>
    <form action="/users" method="get">
        <input type="submit" value="Вернуться на главную страницу" align="center"/>
    </form>
</center>

</body>
</html>
