<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ошибка</title>
</head>
<body>
<hr>
<p align="center">Ошибка! ${sessionScope.resultMessage}</p>
<hr/>
<center>
    <form action="/" method="get">
        <input type="submit" value="Вернуться на главную страницу" align="center"/>
    </form>
</center>
</body>
</html>