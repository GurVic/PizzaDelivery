<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title></title>
    <link rel="stylesheet" href="../../css/bootstrap.css"/>
    <link rel="stylesheet" href="../../css/bootstrap-theme.css"/>
</head>
<br>
    User name: ${authname} <br/>
    User roles: ${authroles} <br/>
    Cart: ${cart.cart} <br/>
    Order ${cart.totalSum} <br/>
    <c:if test="${cart.itemsCount gt 0}">
        <a href="order/create/">Place order</a><br/>
    </c:if>
    <div class="panel">
    <table class="table table-striped">
        <thead>
            <th>#</th>
            <th>Name</th>
            <th>Type</th>
            <th>Price</th>
            <th></th>
        </thead>
        <tbody>
        <c:forEach var="pizza" items="${pizzas}">
        <tr>
            <td>${pizza.id}</td>
            <td>${pizza.name}</td>
            <td>${pizza.type}</td>
            <td>${pizza.price}</td>
            <td>
            <sec:authorize access="hasRole('USER')">
                <form method="post">
                    <input type="hidden" name="pizzaId" value="${pizza.id}" />
                    <input type="text" name="amount" value="0" />
                    <input type="submit" value="Add to cart"
                           <c:if test="${cart.itemsCount gt 10}" >disabled</c:if> />
                </form>
            </sec:authorize>

            <sec:authorize access="hasRole('ADMIN')">
                <form action="edit" method="get">
                    <input type="hidden" name="pizzaid" value="${pizza.id}" />
                    <input type="submit" value="Edit" />
                </form>
                <%--<a href="edit?pizzaid=${pizza.id}">Update</a>--%>
            </sec:authorize>
            </td>
        </tr>
        </c:forEach>
        </tbody>

    </table>
    </div>
    <sec:authorize access="hasRole('ADMIN')">
        <a href="create"> Create new pizza </a> <br/>
        <a href="order/">View all orders </a> <br/>
    </sec:authorize>
    <sec:authorize access="hasRole('USER')">
        <a href="order/userorders">View orders</a> <br/>
    </sec:authorize>

<c:url var="logoutUrl" value="/logout" />
<form action="${logoutUrl}" method="post">
    <input type="submit" value="log out" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
</body>
</html>
