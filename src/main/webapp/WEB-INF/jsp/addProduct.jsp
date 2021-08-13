<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">

</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<div class="page-title">New Product</div>

<div class="product-container">
    <form:form modelAttribute="productPostDto" method="POST" enctype="multipart/form-data">
        <table style="text-align:center;" align="center">
            <tr>
                <td>Name *</td>
                <td><form:input cssStyle="font-size: 18px" path="name"/></td>
            </tr>
            <tr>
                <td>Price *</td>
                <td><form:input cssStyle="font-size: 18px" path="price"/></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>
                    <style>
                        button[type="submit"] {
                            font-size: 18px;
                            font-family: "Arial Rounded MT Bold", Arial, Helvetica, sans-serif
                        }

                        button[type="reset"] {
                            font-size: 18px;
                            font-family: "Arial Rounded MT Bold", Arial, Helvetica, sans-serif
                        }
                    </style>
                    <button type="submit" value="Submit">Add</button>
                    <button type="reset" value="Reset">Reset</button>
                </td>
            </tr>
        </table>
    </form:form>
</div>

<jsp:include page="_footer.jsp"/>

</body>
</html>