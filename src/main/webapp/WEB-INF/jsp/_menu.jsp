<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>


<div class="menu-container">

    <a href="${pageContext.request.contextPath}/shopping-cart/">Home</a>

    <security:authorize access="hasAnyAuthority('ADMIN', 'USER')">
        |
        <a href="/shopping-cart/product/list">
            Product List
        </a>
    </security:authorize>

    <security:authorize access="hasAuthority('USER')">
        |
        <a href="/shopping-cart/cart/">
            My Cart
        </a>
    </security:authorize>

    <security:authorize access="hasAuthority('ADMIN')">
        |
        <a href="/shopping-cart/person/list">
            Person List
        </a>
    </security:authorize>

    <security:authorize access="hasAuthority('ADMIN')">
        |
        <a href="/shopping-cart/product/add">
            Create Product
        </a>
    </security:authorize>

    <security:authorize access="hasAnyAuthority('ADMIN', 'USER')">
        |
        <a href="/shopping-cart/person/profile">Profile</a>
    </security:authorize>

</div>