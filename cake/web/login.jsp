<%--
  Created by IntelliJ IDEA.
  User: Huawei
  Date: 2024/10/24
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
  </head>
  <body>
  <jsp:include page="header.jsp"></jsp:include>
<%--  这个是备份--%>
        <div class="container ">
          <form action="LoginServlet" method="post">
            <div class="register-top-grid">
              <h3>用户登录</h3>
              <div class="input">
                <span>用户名 <label >*</label></span>
                <input type="text" name="username" placeholder="请输入用户名" required="required">
              </div>
              <div class="input">
                <span>密码 <label >*</label></span>
                <input type="password" name="password" placeholder="请输入密码" required="required">
                <span>${msg1}</span>
              </div>
              <div class="clearfix"> </div>
            </div>
            <div class="register-but text-center">
              <input type="submit" value="提交">
              <div class="clearfix"> </div>
            </div>
          </form>
        </div>
        <div class="text-center" style="margin-top: 20px;">
          <a href="admin1.jsp" class="btn btn-info">管理员入口</a>
        </div>
        </div>
  <jsp:include page="footer.jsp"></jsp:include>
  </body>
<script>
  // window.onload = function() {
  //   document.getElementById('loginForm').reset();
  // }
</script>
</html>
