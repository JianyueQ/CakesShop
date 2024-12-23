<%--
  Created by IntelliJ IDEA.
  User: yuxia
  Date: 2024/12/20
  Time: 上午12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%--    <meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <%--    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">--%>
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="register-but">
    <input type="button" value="返回用户登录" onclick="window.location.href='login.jsp'" class="btn-return">
</div>
<div class="container ">
    <form action="AdminLoginServlet1" method="post">
        <%--        <input type="hidden" name="userType" value="admin">--%>
        <div class="register-top-grid">
            <h3>管理员登录</h3>
            <div class="input">
                <span>管理员账号 <label >*</label></span>
                <input type="text" name="username" placeholder="请输入账号" required="required">
            </div>
            <div class="input">
                <span>密码 <label >*</label></span>
                <input type="password" name="password" placeholder="请输入密码" required="required">
                <c:if test="${not empty errorMsg}">
                    <span style="color: red;">${errorMsg}</span>
                </c:if>
                <%--                <span>${msg1}</span>--%>
            </div>

            <div class="clearfix"> </div>
        </div>
        <div class="register-but text-center">
            <input type="submit" value="提交">
            <div class="clearfix"> </div>
        </div>
    </form>
</div>
</body>
</html>
