<%--
  Created by IntelliJ IDEA.
  User: 彼岸有霞
  Date: 2024/12/7
  Time: 下午3:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="../../out/artifacts/cake_war_exploded/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="../../out/artifacts/cake_war_exploded/css/style.css">
</head>
<body>
    <jsp:include page="../../out/artifacts/cake_war_exploded/header.jsp"></jsp:include>

    <h3>用户注册成功，以下是注册信息：</h3>
<%--    <p>用户名：<jsp:getProperty property="username" name="user"/></p>--%>
<%--    <p>密码：<jsp:getProperty property="password" name="user"/></p>--%>
<%--    <p>邮箱：<jsp:getProperty property="email" name="user"/></p>--%>
<%--    <p>收货人：<jsp:getProperty property="name" name="user"/></p>--%>
<%--    <p>收货电话：<jsp:getProperty property="phone" name="user"/></p>--%>
<%--    <p>收货地址：<jsp:getProperty property="address" name="user"/></p>--%>

    <p>用户名：<c:out value="${user.username}"/></p>
    <p>密码：<c:out value="${user.password}"/></p>
    <p>邮箱：<c:out value="${user.email}"/></p>
    <p>收货人：<c:out value="${user.name}"/></p>
    <p>收货电话：<c:out value="${user.phone}"/></p>
    <p>收货地址：<c:out value="${user.address}"/></p>

    <jsp:include page="../../out/artifacts/cake_war_exploded/footer.jsp"></jsp:include>
</body>
</html>
