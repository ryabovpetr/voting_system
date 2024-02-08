<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Restaurants</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a>
    </h3>
    <hr/>
    <h2>Restaurants</h2>
    <a href="restaurants?action=create">Add Restaurant</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th>Lunch Menu</th>
            <th>Votes</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.restaurants}" var="restaurant">
            <jsp:useBean id="restaurant" type="model.Restaurant"/>
            <tr>
                <td>${restaurant.name}</td>
                <td>
                    <c:forEach items="${restaurant.lunchMenu}" var="dish">
                        <li>${dish.key} - ${dish.value} руб.</li>
                    </c:forEach>
                </td>
                <td>${restaurant.countVotes}</td>
                <td><a href="restaurants?action=update&id=${restaurant.id}">Update</a></td>
                <td><a href="restaurants?action=delete&id=${restaurant.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>

<h3><a href="index.html">Home</a>
</h3>
</body>
</html>
