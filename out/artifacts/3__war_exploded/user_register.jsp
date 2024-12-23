<%--
  Created by IntelliJ IDEA.
  User: 彼岸有霞
  Date: 2024/12/7
  Time: 下午1:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <form action="RegisterServlet" method="post">
        <div class="account">
            <div class="container">
                <div class="register">
                        <div class="register-top-grid">
                            <h3>注册新用户</h3>

                            <div class="input">
                                <span>角色选择 <label style="color:red;">*</label></span>
                                <div class="role-selection">
                                    <input type="radio" name="role" value="消费者"
                                    ${empty user.role || user.role == '消费者' ? 'checked' : ''}
                                           onchange="handleRoleChange()" required> 消费者
                                    <input type="radio" name="role" value="商家"
                                    ${user.role == '商家' ? 'checked' : ''}
                                           onchange="handleRoleChange()"> 商家
                                    <input type="radio" name="role" value="管理员"
                                    ${user.role == '管理员' ? 'checked' : ''}
                                           onchange="handleRoleChange()"> 管理员
                                </div>
                                <c:if test="${not empty errorMessages.roleError}">
                                    <span style="color: red;">${errorMessages.roleError}</span>
                                </c:if>
                            </div>

                            <div class="input">
                                <span>用户名 <label style="color:red;">*</label></span>
                                <input name="username" placeholder="请输入用户名" required="required" type="text" value="${user.username}">
                                <c:if test="${not empty errorMessages.usernameError}">
                                    <span style="color: red;">${errorMessages.usernameError}</span>
                                </c:if>
                            </div>
                            <div class="input">
                                <span>邮箱 <label style="color:red;">*</label></span>
                                <input name="email" placeholder="请输入邮箱" required="required" type="text" value="${user.email}">
                                <c:if test="${not empty errorMessages.emailError}">
                                    <span style="color: red;">${errorMessages.emailError}</span>
                                </c:if>

                            </div>
                            <div class="input">
                                <span>密码 <label style="color:red;">*</label></span>
                                <input name="password" placeholder="请输入密码" required="required" type="password" value="${user.password}">
                                <c:if test="${not empty errorMessages.passwordError}">
                                    <span style="color: red;">${errorMessages.passwordError}</span>
                                </c:if>
                            </div>
           <%--这个是备份--%>
<%--                            <div class="input">--%>
<%--                                <span>收货人<label></label></span>--%>
<%--                                <input name="name" placeholder="请输入收货" type="text" value="${user.name}">--%>
<%--                            </div>--%>

<%--                            <div class="input">--%>
<%--                                <span>收货电话<label></label></span>--%>
<%--                                <input name="phone" placeholder="请输入收货电话" type="text" value="${user.phone}">--%>
<%--                                <c:if test="${not empty errorMessages.phoneError}">--%>
<%--                                    <span style="color: red;">${errorMessages.phoneError}</span>--%>
<%--                                </c:if>--%>
<%--                            </div>--%>

<%--                            <div class="input">--%>
<%--                                <span>收货地址<label></label></span>--%>
<%--                                <input name="address" placeholder="请输入收货地址" type="text" value="${user.address}">--%>
<%--                            </div>--%>

                            <div id="dynamicFields">
                                <div class="input">
                                    <span id="nameLabel">收货人</span>
                                    <input id="nameInput" name="name" placeholder="请输入收货人姓名"
                                           type="text" value="${user.name}">
                                </div>
                                <div class="input">
                                    <span id="phoneLabel">收货电话</span>
                                    <input id="phoneInput" name="phone" placeholder="请输入收货电话"
                                           type="text" value="${user.phone}">
                                    <c:if test="${not empty errorMessages.phoneError}">
                                        <span style="color: red;">${errorMessages.phoneError}</span>
                                    </c:if>
                                </div>
                                <div class="input">
                                    <span id="addressLabel">收货地址</span>
                                    <input id="addressInput" name="address" placeholder="请输入收货地址"
                                           type="text" value="${user.address}">
                                </div>
                            </div>

                            <div class="clearfix"></div>
                        </div>


                        <div class="register-but text-center">
                            <input  type="submit" value="提交">
                            <div class="clearfix"></div>
                        </div>

                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </form>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
    function handleRoleChange() {
        var role = document.querySelector('input[name="role"]:checked').value;
        var dynamicFields = document.getElementById('dynamicFields');
        var form = document.getElementById('registerForm');

        if (role === '管理员'){
            window.location.href='admin.jsp';
            return false;
        }else if (role === '商家'){
            document.getElementById('nameLabel').innerHTML = '店铺名称 <label style="color:red;">*</label>';
            document.getElementById('phoneLabel').innerHTML = '商户电话 <label style="color:red;">*</label>';
            document.getElementById('addressLabel').innerHTML = '商户地址 <label style="color:red;">*</label>';

            // 更改placeholder
            document.getElementById('nameInput').placeholder = '请输入店铺名称';
            document.getElementById('phoneInput').placeholder = '请输入商户电话';
            document.getElementById('addressInput').placeholder = '请输入商户地址';

            // 设置为必填
            document.getElementById('nameInput').required = true;
            document.getElementById('phoneInput').required = true;
            document.getElementById('addressInput').required = true;
        }else {
            document.getElementById('nameLabel').innerHTML = '收货人';
            document.getElementById('phoneLabel').innerHTML = '收货电话';
            document.getElementById('addressLabel').innerHTML = '收货地址';

            // 更改placeholder
            document.getElementById('nameInput').placeholder = '请输入收货人姓名';
            document.getElementById('phoneInput').placeholder = '请输入收货电话';
            document.getElementById('addressInput').placeholder = '请输入收货地址';

            // 设置为非必填
            document.getElementById('nameInput').required = false;
            document.getElementById('phoneInput').required = false;
            document.getElementById('addressInput').required = false;
        }

    }
</script>
</html>
