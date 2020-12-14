<%--
  Created by IntelliJ IDEA.
  User: Fruzsi
  Date: 2020. 12. 14.
  Time: 3:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>${dino.id}</title>
</head>
<body>
<table>
    <tr><td>Name:</td><td>${dino.name}</td></tr>
    <tr><td>Diet:</td><td>${dino.diet}</td></tr>
    <tr><td>Length:</td><td>${dino.length}</td></tr>
    <tr><td>Height:</td><td>${dino.height}</td></tr>
    <tr><td>Weight:</td><td>${dino.weight}</td></tr>
    <tr><td>Registered on:</td><td>${dino.registered}</td></tr>
</table>
<form action="${pageContext.servletContext.contextPath}/dinos">
    <input type="submit" value="Back">
</form>

</body>
</html>
