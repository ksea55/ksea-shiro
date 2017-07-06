<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>list</title>
</head>
<body>
login page

Welcome:<shiro:principal></shiro:principal>
<br/>
<shiro:hasRole name="admin">
    <a href="${pageContext.request.contextPath}/home/admin/index">admin index</a>
    <br/>
</shiro:hasRole>

<shiro:hasRole name="user">

    <a href="${pageContext.request.contextPath}/home/user/index">user index</a>
    <br/>
</shiro:hasRole>

<a href="${pageContext.request.contextPath}/login/logout">login out</a>
</body>
</html>
