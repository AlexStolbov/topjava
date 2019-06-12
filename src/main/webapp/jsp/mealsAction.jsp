<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
    <table>
        <tr>
            <th>Описание</th>
            <th>Время приема</th>
            <th>Калории</th>
            <th colspan=2>Action</th>
        </tr>
        <c:forEach var="meal" items="${meals}">
            <tr class = ${meal.isExcess() ? "red" : "green"}>
                <td>${meal.description}</td>
                <td>${formatter.format(meal.dateTime)}</td>
                <td>${meal.calories}</td>
                <td><a href="mealsedit?action=edit&mealUuid=${meal.uuid}">Edit</a></td>
                <td><a href="mealsedit?action=delete&mealUuid=${meal.uuid}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <h3><a href="mealsedit?action=insert">Add meal</a></h3>
</body>
</html>
