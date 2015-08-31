<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Edit pizza</title>
</head>
<body>
<form class="form-horizontal" method="post" action="edit">
    <input type="hidden" name="id" value="${pizza.id}"/>
    Name : <input type="text" name="name" value="${pizza.name}"/></br>
    Price : <input type="text" name="price" value="${pizza.price}"/></br>
    Type :
    <select name="type">
        <c:forEach var="tpe" items="${pizzatypes}">
            <option value="${tpe}" <c:if test="${tpe eq pizza.type}">selected</c:if> >${tpe}</option>
        </c:forEach>
    </select></br>
    <sec:csrfInput />
    <input type="submit" value="Edit">
</form>
</body>
</html>
