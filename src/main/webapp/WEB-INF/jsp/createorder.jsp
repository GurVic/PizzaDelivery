<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title></title>
</head>
<body>
    User name: ${authname} <br/>
    Cart: ${cart.cart} <br/>
    Order ${cart.totalSum} <br/>

    <form method="post">
        Address Line 1 <input type="text" name="addressLine1" value=""/><br/>
        Address Line 2 <input type="text" name="addressLine2" value=""/><br/>
        City <input type="text" name="city" value=""/><br/>
        State <input type="text" name="state" value=""/><br/>
        Zip <input type="number" name="zipCode" value=""/><br/>
        <input type="submit" value="Locate"/><br/>
    </form>
</body>
</html>
