<%--
  Created by IntelliJ IDEA.
  User: Fruzsi
  Date: 2020. 12. 14.
  Time: 2:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Dinosaurs</title>
</head>
<body>
<c:if test="${!empty dinos}">
    <table frame="border" rules="all">
        <tr><th>ID</th><th>Name</th><th>Diet</th><th>Length</th><th>Height</th><th>Weight</th><th>Registered on</th></tr>
        <c:forEach items="${dinos}" var="dino">
            <tr><td><a href="${pageContext.servletContext.contextPath}/dino/${dino.id}">${dino.id}</a></td><td>${dino.name}</td><td>${dino.diet}</td><td>${dino.length}</td><td>${dino.height}</td><td>${dino.weight}</td><td>${dino.registered}</td></tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty dinos}">
    <c:out value="We don't have dinosaurs! :("/>
</c:if>

<form action="${pageContext.servletContext.contextPath}/addDino">
    <input type="submit" value="Add dinosaur">
</form>

</body>
</html>
