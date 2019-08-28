
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<head>
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
            Login:
            <c:out value="${user.login}"/> <br/>
            Password:
            <c:out value="${user.password}"/> <br/>
        </td>
        <td>
            <form action="updateUser.jsp" method="post">
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="hidden" name="name" value="${user.name}"/>
                <input type="hidden" name="login" value="${user.login}">
                <input type="hidden" name="password" value="${user.password}"/>
                <input type="submit" value="Изменить"/>
            </form>

            <form action="deleteUser.jsp" method="post">
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="hidden" name="login" value="${user.login}"/>
                <input type="submit" value="Удалить"/>
            </form>

        </td>

        </c:forEach>
</table>
<center>
    <form action="addUser.jsp" method="post">
        <input type="submit" value="Добавить пользователся" style="left: auto"/>
    </form>
</center>
</body>
</html>
