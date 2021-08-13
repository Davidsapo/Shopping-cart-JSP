<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<fmt:setLocale value="en_US" scope="session"/>

<div class="page-title">My Cart</div>

<c:if test="${empty cart or empty cart.cartItems}">
    <div class="customer-info-container">
        <h3 class="title-class">There is no items in Cart</h3>
        <a class="order-button" href="/shopping-cart/product/list">Show Product List</a>
    </div>

</c:if>

<c:if test="${not empty cart and not empty cart.cartItems   }">
    <form:form method="POST" modelAttribute="cart" action="/shopping-cart/cart/update-cart">

        <c:forEach items="${cart.cartItems}" var="cartItem" varStatus="varStatus">
            <div class="cart-item-container">
                <ul>
                    <li>Name: ${cartItem.product.name}</li>
                    <li>Price: ${cartItem.product.price}</li>
                    <li>Quantity: <form:input cssStyle="font-size: 18px; width: 30px"
                                              path="cartItems[${varStatus.index}].quantity"/></li>
                    <li>Subtotal: ${cartItem.quantity * cartItem.product.price}</li>
                    <li><a class="delete-button" href="/shopping-cart/cart/delete-cart-item?cartItemId=${cartItem.id}">Delete </a>
                    </li>
                </ul>
            </div>
        </c:forEach>

        <div class="customer-info-container">
            <h3>Cart Summary:</h3>
            <ul>
                <li>Quantity: ${cart.cartItems.size()}</li>
                <li>Total:
                    <span class="total">
            <fmt:formatNumber value="${cart.totalPrice}" type="currency"/>
          </span></li>
                <button class="update-button" type="submit">Update</button>
            </ul>
        </div>

        <div style="clear: both"></div>
        <ul>
            <li><a class="order-button" href="/shopping-cart/cart/order">Order</a></li>
            <li><a class="empty-button" href="/shopping-cart/cart/empty">Empty cart</a></li>
        </ul>
    </form:form>

</c:if>

<jsp:include page="_footer.jsp"/>

</body>
</html>