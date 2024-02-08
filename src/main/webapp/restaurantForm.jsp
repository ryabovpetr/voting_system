<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Restaurant</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'Create restaurant' : 'Edit restaurant'}</h2>
    <jsp:useBean id="restaurant" type="model.Restaurant" scope="request"/>
    <form method="post" action="restaurants">
        <input type="hidden" name="id" value="${restaurant.id}">
        <input type="hidden" name="countVotes" value="${restaurant.countVotes}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${restaurant.name}" size=50 name="name" required></dd>
        </dl>
        <dl>
            <dt>Lunch Menu:</dt><br>
            <c:set var="status" value="${-1}" scope="page"/>
            <c:forEach var="entryDish" items="${restaurant.lunchMenu}" varStatus="status">
                <label for="editDishName">Блюдо</label>
                <input type="text" id="editDishName" name="editDishName${status.index}" value="${entryDish.key}">
                <label for="editDishPrice">Ценой</label>
                <input type="number" id="editDishPrice" name="editDishPrice${status.index}" value="${entryDish.value}">
                <label for="editDishPrice">руб.</label>
                <input type="hidden" name="editDishKey${status.index}" value="${entryDish.key}">

                <button type="submit" name="action" value="editDish:${status.index}">Применить изменения</button>
                <button type="submit" name="action" value="deleteDish:${status.index}">Удалить</button><br>
            </c:forEach>

            <label for="newDishName">Новое блюдо:</label>
            <input type="text" id="newDishName" name="newDishName">
            <label for="newDishPrice">Цена нового блюда:</label>
            <input type="number" id="newDishPrice" name="newDishPrice">
            <button type="submit" name="action" value="addDish">Добавить блюдо</button>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
