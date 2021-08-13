<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<div class="page-title">Login</div>

<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect("/shopping-cart/person/profile"); %>
</sec:authorize>
<div class="login-container">
    <form method="POST" action="/login">
        <table align="center">
            <tr>
                <td><label>Username: </label></td>
                <style>
                    input[type="text"] {
                        font-size: 18px;
                    }
                </style>
                <td><input name="username" type="text" placeholder="Username" autofocus="true"/></td>
            </tr>

            <tr>
                <style>
                    input[type="password"] {
                        font-size: 18px;
                    }
                </style>
                <td><label>Password: </label></td>
                <td><input name="password" type="password" placeholder="Password"/></td>
            </tr>

            <tr>
                <td colspan="3">
                    <style>
                        button[type="submit"] {
                            font-size: 18px;
                            font-family: "Arial Rounded MT Bold", Arial, Helvetica, sans-serif
                        }
                    </style>
                    <button type="submit">Log In</button>
                </td>
            </tr>
        </table>
    </form>
</div>

<jsp:include page="_footer.jsp"/>
</body>
</html>