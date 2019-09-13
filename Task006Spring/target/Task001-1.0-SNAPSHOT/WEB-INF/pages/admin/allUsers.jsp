<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Все пользователи</title>
</head>
<body>
<hr>
<p align="center">Все пользователи</p>
<hr/>
<table cellspacing="5" border="5" align="center">
    <c:forEach items="${requestScope.simpleUsers}" var="user">
    <tr>
        <td>
            Name: <c:out value="${user.name}"/><br/>
            Login: <c:out value="${user.login}"/> <br/>
            Password: <c:out value="${user.password}"/> <br/>
        </td>
        <td>
            <form action="/admin/users/update" method="get">
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="submit" value="Изменить"/>
            </form>

            <form action="/admin/users/delete" method="get">
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="submit" value="Удалить"/>
            </form>

        </td>

        </c:forEach>
</table>
<center>
    <form action="/admin/users/add" method="get">
        <input type="submit" value="Добавить пользователся"/>
    </form>
</center>
<center>
    <form action="${pageContext.request.contextPath}/logout" method="get">
        <input type="submit" value="Выйти"/>
    </form>
</center>
</body>
</html>
