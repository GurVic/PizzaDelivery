<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Cart</title>
    <link rel="stylesheet" href="../../css/bootstrap.css"/>
    <link rel="stylesheet" href="../../css/bootstrap-theme.css"/>
</head>
<body>
    <div class="panel">
	    <table class="table table-striped">
		    <thead>
			    <th>Name</th>
			    <th>Price</th>
			    <th>Type</th>
			    <th>Amount</th>
		    </thead>
		    <c:forEach items="${cart.cart}" var="pizza">
			<tr>
				<td>${pizza.key.name}</td>
				<td>${pizza.key.price * pizza.value}</td>
				<td>${pizza.key.type}</td>
				<td>
                    <form action="cart/edit" method="post">
						<input type="hidden" name="pizzaId" value="${pizza.key.id}" />
                        <input type="text" name="amount" value="${pizza.value}" />
                        <input type="submit" value="Save" />
					</form>
                </td>
				<td>
					<form action="/profile/cart/remove" method="post">
						<input type="hidden" name="pizzaId" value="${pizza.key.id}" />
                        <input type="submit" value="Remove" />
					</form>
				</td>
			</tr>
		    </c:forEach>
	</table>
	Order <br/>
        <b>
		<c:choose>
			<c:when test="${cart.itemsCount le 10}">${cart.totalSum}</c:when>
			<c:otherwise>Maximum 10 pizzas</c:otherwise>
		</c:choose>
        </b>
	<form method="post">
		<input type="submit" value="Place order"
			<c:if test="${cart.itemsCount le 0 or cart.itemsCount gt 10}" >disabled</c:if> />
	</form>
	<a href="./">Back</a>
    </div>
</body>
</html>