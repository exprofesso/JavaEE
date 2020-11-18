<%--
  Created by IntelliJ IDEA.
  User: sergeypodkolzin
  Date: 11/18/20
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>
<body>
<h3>Welcome, Enter The User Details</h3>

<form:form method="POST"
           action="/cars" modelAttribute="carsCreateRequest">
    <table>
        <tr>
            <td><form:label path="model">Model</form:label></td>
            <td><form:input type="text" path="model"/>${carsCreateRequest.model}</td>
        </tr>
        <tr>
            <td><form:label path="creationYear">
                CreationYear</form:label></td>
            <td><form:input type="number" path="creationYear"/></td>
        </tr>
        <tr>
            <td><form:label path="userId">
                UserId</form:label></td>
            <td><form:input type="number" path="userId"/></td>
        </tr>
        <tr>
            <td><form:label path="price">
                Price</form:label></td>
            <td><form:input type="number" path="price"/></td>
        </tr>
        <tr>
            <td><form:label path="color">
                Color</form:label></td>
            <td><form:input type="text" path="color"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>

</body>
</html>