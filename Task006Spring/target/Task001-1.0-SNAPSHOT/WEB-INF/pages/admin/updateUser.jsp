<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменяем данные пользователя</title>
</head>
<body>
<hr>
<p align="center">Изменяем данные пользователя <c:out value="${param.login}"/> <br/></p>
<hr/>
<c:set var="role" value="${requestScope.role}" scope="page"/>

<center>
    <form action="/admin/users/update" method="post">
        <input type="hidden" name="id" value="${requestScope.user.id}"/>
        Name:<input type="text" name="name" value="${requestScope.user.name}"/>
        Login:<input type="text" name="login" value="${requestScope.user.login}"/>
        Password:<input type="text" name="password" value="${requestScope.user.password}"/>
        Role:
        <c:forEach items="${requestScope.simpleRoles}" var="role">
            <label for="${role.ordinal()}">${role.getPresentationName()}</label>
            <input type="checkbox" id="${role.ordinal()}" name="checkBoxParameter ${role.ordinal()}"
                   value="${role.name()}"
                <c:out value="${requestScope.user.rolesIsExisName(role)?'checked':'false'}"/>>
        </c:forEach>
        <br>
        <br>
        <input type="submit" value="Изменить"/>
    </form>

    <form action="/" method="get">
        <input type="submit" value="Отмена"/>
    </form>
</center>
</body>
</html>