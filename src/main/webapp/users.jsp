<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Users</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a>
    </h3>
    <hr/>
    <h2>Users</h2>
    <a href="users?action=create">Add User</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Email</th>
            <th>Role</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.users}" var="user">
            <jsp:useBean id="user" type="rpg.model.User"/>
            <tr>
                <td>${user.email}</td>
                <td>${user.role}</td>
                <td><a href="users?action=update&id=${user.id}">Update</a></td>
                <td><a href="users?action=delete&id=${user.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>

<h3><a href="index.html">Home</a></h3>
</body>
</html>
