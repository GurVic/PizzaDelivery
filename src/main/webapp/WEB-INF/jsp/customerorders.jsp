<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<link rel="stylesheet" href="../../../css/bootstrap.css"/>
	<link rel="stylesheet" href="../../../css/bootstrap-theme.css"/>
	<title>User Orders</title>
</head>
<body>
	<table class="table table-striped">
		<thead>
			<th>#</th>
			<th>Pizza</th>
			<th>Amount</th>
			<th>Price</th>
			<th>Discount</th>
		</thead>
		<c:forEach items="${orders}" var="order">
			<tr>
				<td>${order.orderId}</td>
				<td>
					<table>
						<c:forEach items="${order.items}" var="pizza">
							<tr>
								<td>${pizza.key.name}</td>
							</tr>
						</c:forEach>
					</table>
				</td>
				<td>
					<table>
						<c:forEach items="${order.items}" var="pizza">
							<tr>
								<td>${pizza.value}</td>
							</tr>
						</c:forEach>
					</table>
				</td>
				<td>${order.price}</td>
				<td>${order.discount}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="../">Back</a> to store.
</body>
</html>