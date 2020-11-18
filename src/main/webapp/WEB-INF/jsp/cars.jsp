<%--
  Created by IntelliJ IDEA.
  User: sergeypodkolzin
  Date: 10/17/20
  Time: 10:55 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cars user</title>
</head>
<body>
<div>
    <h1>CARS</h1>
</div>
<div>
    <table>
        <tr>
            <td>id</td>
            <td>model</td>
            <td>creation year</td>
            <td>User ID</td>
            <td>Price</td>
            <td>Color</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
        <c:forEach var="cars" items="${cars}">
            <tr>
                <td>${cars.id}</td>
                <td>${cars.model}</td>
                <td>${cars.creationYear}</td>
                <td>${cars.userId}</td>
                <td>${cars.price}</td>
                <td>${cars.color}</td>
                <td><button>Edit</button></td>
                <td><button>Delete</button></td>
            </tr>
        </c:forEach>
    </table>
</div>
<div>
    ${singleCars}
</div>
</body>
</html>