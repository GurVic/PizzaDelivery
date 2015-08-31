<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title></title>
    <link rel="stylesheet" href="../../css/bootstrap.css"/>
    <link rel="stylesheet" href="../../css/bootstrap-theme.css"/>
</head>
<body>
    <div class="panel">
    <table class="table table-striped">
        <thead>
            <th>#</th>
            <th>Date</th>
            <th>Status</th>
            <th>Pizzas</th>
            <th></th>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.orderId}</td>
            <td>${order.date}</td>
            <td>${order.orderStatus}</td>
            <td>${order.items}</td>
            <td><a href="edit?orderid=${order.orderId}">Update</a></td>
        </tr>
        </c:forEach>
        </tbody>

    </table>
    </div>
<a href="create">Create new order</a>
</body>
</html>
