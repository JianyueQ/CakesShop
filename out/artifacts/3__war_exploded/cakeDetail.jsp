<%--
  Created by IntelliJ IDEA.
  User: Huawei
  Date: 2024/10/24
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
  <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
  <link type="text/css" rel="stylesheet" href="css/style.css">
  <link type="text/css" rel="stylesheet" href="css/flexslider.css">

</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="single">
  <div class="container">
    <div class="single-grids">
      <div class="col-md-8 single-grid">

              <img src="./pic/1-1.jpg" />

      </div>
      <div class="col-md-4 single-grid simpleCart_shelfItem">
        <h3>蛋糕名称</h3>
        <p>蛋糕描述信息</p>
        <div class="galry">
          <div class="prices">
            <h5 class="item_price">蛋糕价格</h5>
          </div>
          <div class="clearfix"></div>
        </div>
        <div class="btn_form">
          <a href="#" class="add-cart item_add" >加入购物车</a>
        </div>
      </div>

      <div class="clearfix"> </div>
    </div>
  </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
