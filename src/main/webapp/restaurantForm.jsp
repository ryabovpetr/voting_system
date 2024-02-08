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
            <c:forEach var="entryDish" items="${restaurant.lunchMenu}">
                <label for="editDishName">Блюдо</label>
                <input type="text" id="editDishName" name="editDishName" value="${entryDish.key}">
                <label for="editDishPrice">Ценой</label>
                <input type="number" id="editDishPrice" name="editDishPrice" value="${entryDish.value}">
                <label for="editDishPrice">руб.</label>
                <input type="hidden" name="editDishKey" value="${entryDish.key}"><br>
            </c:forEach>

            <div id="newDishesContainer">
                <div id="newDish1" style="display: none;">
                <label for="newDishName1">Новое блюдо:</label>
                <input type="text" id="newDishName1" name="newDishName1">
                <label for="newDishPrice1">Цена нового блюда:</label>
                <input type="number" id="newDishPrice1"  name="newDishPrice1"><br>
                </div>

                <div id="newDish2" style="display: none;">
                    <label for="newDishName2">Новое блюдо:</label>
                    <input type="text" id="newDishName2" name="newDishName2">
                    <label for="newDishPrice2">Цена нового блюда:</label>
                    <input type="number" id="newDishPrice2"  name="newDishPrice2"><br>
                </div>

                <div id="newDish3" style="display: none;">
                    <label for="newDishName3">Новое блюдо:</label>
                    <input type="text" id="newDishName3" name="newDishName3">
                    <label for="newDishPrice3">Цена нового блюда:</label>
                    <input type="number" id="newDishPrice3"  name="newDishPrice3"><br>
                </div>
            </div>

            <button type="button" onclick="showNewDishFields()">Добавить блюдо</button>

        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>


</section>

</body>
<script>
    let newDishCount = 0;
    function showNewDishFields() {
        if (newDishCount < 3) {
            newDishCount++;
            document.querySelector('[name=newDishName' + newDishCount + ']').closest('div').style.display = 'block';
        }
    }
</script>
</html>
