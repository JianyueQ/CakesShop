<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="cart-items">
    <div class="container">

        <h2>我的购物车</h2>
    <c:forEach items="${gou}" var="cake">
            <div class="cart-header col-md-6">

                <div class="cart-sec simpleCart_shelfItem">
                    <div class="cart-item cyc">
                        <a href="#">
                            <img src="${cake.image1}" class="img-responsive">
                        </a>
                    </div>
                    <div class="cart-item-info">
                        <h3><a href="#"> ${cake.name}</a></h3>
                        <h3><span>单价: ¥${cake.price}</span></h3>
                        <h3><span>${cake.intro} </span></h3>
                        <a class="btn btn-info" >增加</a>
                        <a class="btn btn-warning" >减少</a>
                        <a class="btn btn-danger" >删除</a>
                    </div>
                    <div class="clearfix"></div>
                </div>

            </div>

    </c:forEach>

        <div class="cart-header col-md-12">
            <hr>
            <h3>订单总金额: ¥ </h3>
            <a class="btn btn-success btn-lg" style="margin-left:74%" href="/order_submit">提交订单</a>
        </div>



    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
