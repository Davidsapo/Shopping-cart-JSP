<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<fmt:setLocale value="en_US" scope="session"/>

<div class="page-title">Product List</div>

<c:forEach items="${products}" var="prodInfo">
    <div class="product-preview-container">
        <ul>
            <li>Name: ${prodInfo.name}</li>
            <li>Price: ${prodInfo.price}</li>
            <security:authorize access="hasAuthority('USER')">
            <li><a class="add-button" href="/shopping-cart/cart/add-product?productId=${prodInfo.id}">Add to cart</a></li>
            </security:authorize>
            <security:authorize access="hasAuthority('ADMIN')">
            <li><a class="edit-button" href="/shopping-cart/product/update?id=${prodInfo.id}">Edit</a></li>
            </security:authorize>
            <security:authorize access="hasAuthority('ADMIN')">
            <li><a class="delete-button" href="/shopping-cart/product/delete?id=${prodInfo.id}">Delete</a></li>
            </security:authorize>
        </ul>
    </div>

</c:forEach>
<br/>

<jsp:include page="_footer.jsp"/>

</body>
</html>