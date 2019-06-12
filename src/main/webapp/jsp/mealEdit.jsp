<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Meal edit</title>
</head>
<hr>
<body>
    <form method="post" action="mealsedit" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${meal.uuid}">
        <dl>
            <dt>Описание:</dt>
            <dd><input type="text" name="description" size=50 value="${meal.description}"></dd>
            <dt>Время приема:</dt>
            <dd><input type="datetime-local" name="datetime" size=50 value="${meal.getDateTime()}"></dd>
            <dt>Калории:</dt>
            <dd><input type="text" name="calories" size=50 value="${meal.calories}"></dd>
        </dl>
        <hr>
        <button type="submit" value="save" name="buttonName">Сохранить</button>
        <button type="submit" value="cancel" name="buttonName">Отменить</button>
    </form>
</body>
</html>