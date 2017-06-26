<%--
  Created by IntelliJ IDEA.
  User: ksea
  Date: 2017/6/26
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login/doLogin" method="post">
    用户名:<input type="text" name="userName"><br/>
    密&nbsp;&nbsp;码:<input type="password" name="password"/>
    <input type="submit" value="登录"/>
</form>
</body>
</html>
