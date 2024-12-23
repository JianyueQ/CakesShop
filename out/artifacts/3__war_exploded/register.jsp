<%--
  Created by IntelliJ IDEA.
  User: 彼岸有霞
  Date: 2024/12/8
  Time: 上午10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
      <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
      <link type="text/css" rel="stylesheet" href="css/style.css">
  </head>
  <body>
    <form action="ServletUser"  method="post">
      <div class="account">
          <div class="container">
              <div class="register">
                      <div class="register-top-grid">
                          <h3>注册新用户</h3>
                          <div class="input">
                              <span>用户名 <label style="color:red;">*</label></span>
                              <input name="username" placeholder="请输入用户名" required="required" type="text">
                              <span class="error" id="usernameError"></span>
                          </div>
                          <div class="input">
                              <span>邮箱 <label style="color:red;">*</label></span>
                              <input name="email" placeholder="请输入邮箱" required="required" type="text">
                              <span class="error" id="emailError"></span>
                          </div>
                          <div class="input">
                              <span>密码 <label style="color:red;">*</label></span>
                              <input name="password" placeholder="请输入密码" required="required" type="password">
                              <span class="error" id="passwordError"></span>
                          </div>
                          <div class="input">
                              <span>收货人<label></label></span>
                              <input name="name" placeholder="请输入收货" type="text">
                              <!--							<span id="" class="error"></span>-->
                          </div>
                          <div class="input">
                              <span>收货电话<label></label></span>
                              <input name="phone" placeholder="请输入收货电话" type="text">
                              <span class="error" id="phoneError"></span>
                          </div>
                          <div class="input">
                              <span>收货地址<label></label></span>
                              <input name="address" placeholder="请输入收货地址" type="text">
                              <!--							<span id="" class="error"></span>-->
                          </div>
                          <div class="clearfix"></div>
                      </div>
                      <div class="register-but text-center">
                          <input onclick="return finalValidate()" type="submit" value="提交">
                          <div class="clearfix"></div>
                      </div>

                  <div class="clearfix"></div>
              </div>
          </div>
      </div>
    </form>
  </body>
  <script>
      function finalValidate() {
          var username = document.getElementsByName("username")[0].value;
          var usernameRegex =
              /^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$/
          // (?=)是正向前瞻   $是匹配字符串结尾    +是一个数量限定符，意味着前面的
          // 字符类（也就是英文字母或数字）必须出现一次或多次，这样就保证了用户名不能是空字符串，至少要有英文字母或者数字存在。


          if (!usernameRegex.test(username)) {
              document.getElementById("usernameError").innerHTML = "用户名要求英文字母和数字组成";
              return false;
          }

          var password = document.getElementsByName("password")[0].value;
          var passwordRegex = /^(?=.*[A-Z])(?=.*[^\w])\S{8,}$/;
          if (!passwordRegex.test(password)) {
              document.getElementById("passwordError").innerHTML = "密码至少有一个大写字母和一个符号组成，8个字符以上";
              return false;
          }

          var email = document.getElementsByName("email")[0].value;
          var emailRegex = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
          if (!emailRegex.test(email)) {
              document.getElementById("emailError").innerHTML = "邮箱格式不正确";
              return false;
          }

          var phone = document.getElementsByName("phone")[0].value;
          var phoneRegex = /^\d{11}$/;
          if (!phoneRegex.test(phone)) {
              document.getElementById("phoneError").innerHTML = "收货电话手机号码要求11位数字";
              return false;
          }

          // window.location.href = "success.html";
          // return false;
          return true;

      }
  </script>
</html>
