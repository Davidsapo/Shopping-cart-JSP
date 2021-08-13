<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<jsp:include page="_header.jsp"/>

<jsp:include page="_menu.jsp"/>

<div class="page-title">Order Info</div>

<div class="customer-info-container">
    <h3>Thank You For Order</h3>
    <ul>
        <li>Total price: <span class="total">${cart.totalPrice}</span></li>
    </ul>
</div>

<jsp:include page="_footer.jsp"/>

</body>
</html>