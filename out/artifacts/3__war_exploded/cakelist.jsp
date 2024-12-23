<%--
  Created by IntelliJ IDEA.
  User: Huawei
  Date: 2024/10/24
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="products">
    <div class="container">
        <h2>全部系列</h2>

        <div class="col-md-12 product-model-sec">
<c:forEach items="${cakes}" var="cake">



                <div class="product-grid">
                    <a href="#">
                        <div class="more-product"><span> </span></div>
                        <div class="product-img b-link-stripe b-animate-go  thickbox">
                            <img src="${cake.image1}" class="img-responsive" alt="图片" width="240" height="240">
                            <div class="b-wrapper">
                                <h4 class="b-animate b-from-left  b-delay03">
                                    <button href="#">查看详情</button>
                                </h4>
                            </div>
                        </div>
                    </a>
                    <div class="product-info simpleCart_shelfItem">
                        <div class="product-info-cust prt_name">
                            <h3>${cake.name}</h3>
                            <span class="item_price">${cake.price}</span>
                            <p>${cake.intro}</p>
                            <a href="AddCartServlet?id=${cake.id}"><input type="button" class="item_add items" value="加入购物车" ></a>
                            <div class="clearfix"> </div>
                        </div>
                    </div>

                </div>
</c:forEach>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
