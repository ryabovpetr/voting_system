<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Restaurants</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Restaurants</h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th>LunchMenu</th>
            <th>Votes</th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.meals}" var="restaurant">
            <jsp:useBean id="restaurant" type="model.Restaurant"/>
            <tr>
                <td>${restaurant.name}</td>
                <td>${restaurant.lunchMenu}</td>
                <td>${restaurant.countVotes}</td>
            </tr>
        </c:forEach>
    </table>
</section>

<h3><a href="index.html">Home</a></h3>
</body>
</html>
