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
        </tr>
        <c:forEach var="meal" items="${meals}">
            <tr ${meal.isExcess() ? "class = red" : ""}>
                <td>${meal.description}</td>
                <td>${formatter.format(meal.dateTime)}</td>
                <td>${meal.calories}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
