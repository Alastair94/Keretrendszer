<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Fruzsi
  Date: 2020. 12. 16.
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>

<html>
<head>
    <title>Update Dinosour</title>
</head>
<body>
<form:form method="get" action="${pageContext.servletContext.contextPath}/update/${dino.id}" modelAttribute="dino">
    <form:label path="name">Name</form:label>
    <form:input path="name"/>
    <form:label path="diet">Diet</form:label>
    <form:select path="diet">
        <form:options items="${diets}"/>
    </form:select>
    <form:label path="length">Length</form:label>
    <form:input path="length"/>
    <form:label path="height">Height</form:label>
    <form:input path="height"/>
    <form:label path="weight">Weight</form:label>
    <form:input path="weight"/>

    <input type="submit" value="Update"/>
</form:form>
</body>
</html>
