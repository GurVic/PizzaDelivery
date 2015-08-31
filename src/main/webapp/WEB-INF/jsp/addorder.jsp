<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title></title>
    <script>
        var model = [];
        function addPizzaInOrder() {
            var pizz = {}
            pizz.type = 1;
            model.add(pizz);
        }
    </script>
</head>
<body>
    <form class="form-horizontal" method="post" action="create">
        <input type="hidden" name="id" value="${order.orderId}"/>
        Pizza type :
            <select name="type">
            <c:forEach var="tpe" items="${pizzaType}">
                <option value="${tpe}">${tpe}</option>
            </c:forEach>
            </select>
            <input type="number" min="0.1">
            <div onclick="addPizzaInOrder()">add</div>
        <input type="submit" value="Add">
    </form>
</body>
</html>
