<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Create pizza</title>
</head>
<body>
    <form class="form-horizontal" method="post" action="create">
        Name : <input type="text" name="name"/></br>
        Price : <input type="text" name="price"/></br>
        Type :
            <select name="type">
            <c:forEach var="tpe" items="${pizzatypes}">
                <option value="${tpe}">${tpe}</option>
            </c:forEach>
            </select></br>
            <sec:csrfInput />
        <input type="submit" value="Add">
    </form>
</body>
</html>
