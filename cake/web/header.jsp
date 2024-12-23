<%--
  Created by IntelliJ IDEA.
  User: Huawei
  Date: 2024/11/10
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--header-->
<div class="header">
    <div class="container">
        <nav class="navbar navbar-default" role="navigation">

            <!--navbar-header-->
            <div class=" navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <%-- 未登录状态显示的导航 --%>
                    <c:if test="${empty sessionScope.user}">
                        <li><a href="index.jsp">首页</a></li>
                        <li><a href="login.jsp">登录</a></li>
                        <li><a href="user_register.jsp">注册</a></li>
                    </c:if>

                    <%-- 登录状态显示的导航 --%>
                    <c:if test="${not empty sessionScope.user}">
<%--                    <li><a href="index.jsp">首页</a></li>--%>
                        <li><a href="CakeListServlet">商品展示</a></li>
                    <li><a href="showcart.jsp">购物车</a></li>
                    <li><a href="profile.jsp">个人中心</a></li>
                        <li><a href="QuitServlet">退出登录 </a></li>
                        </c:if>
                </ul>
            </div>
        </nav>
        <div class="clearfix"> </div>
    </div>
</div>
<!--//header-->
