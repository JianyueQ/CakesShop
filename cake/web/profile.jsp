<%--
  Created by IntelliJ IDEA.
  User: yuxia
  Date: 2024/12/22
  Time: 下午7:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>个人中心</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <script src="js/jquery.min.js"></script>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <div class="container">
        <div class="row">
            <!-- 左侧导航 -->
            <div class="col-md-3">
                <div class="profile-section">
                    <div class="list-group">
                        <a href="#" class="list-group-item active" data-section="profile">个人资料</a>
                        <a href="#" class="list-group-item" data-section="avatar">修改头像</a>
                        <c:if test="${user.role == '商家'}">
                            <a href="#" class="list-group-item" data-section="products">商品管理</a>
                        </c:if>
                    </div>

                </div>
            </div>
            <!-- 右侧内容 -->
            <div class="col-md-9">
                <!-- 个人资料部分 -->
                <div class="profile-section" id="profileSection">
                    <h3>个人资料</h3>
                    <!-- you侧导航栏的头像 -->

                    <div class="avatar-container">
                        <img src="
<%--                        ${not empty userAvatar ? userAvatar : 'avatar/default-avatar.jpg'}--%>
                            ${user.avatar}"
                             class="img-responsive"
                             alt="用户头像"
                             width="120"
                             height="120"
                             >
                    </div>
                    <form action="UpdateProfileServlet" method="post">
                        <div class="form-group">
                            <label>用户名</label>
                            <input type="text" class="form-control" name="username"
                                   value="${user.username}" readonly>
                        </div>
                        <div class="form-group">
                            <label>邮箱</label>
                            <input type="email" class="form-control" name="email"
                                   value="${user.email}">
                        </div>
                        <div class="form-group">
                            <label>电话</label>
                            <input type="text" class="form-control" name="phone"
                                   value="${user.phone}">
                        </div>
                        <div class="form-group">
                            <label>地址</label>
                            <input type="text" class="form-control" name="address"
                                   value="${user.address}">
                        </div>
<%--                        <button type="submit" class="btn btn-primary">保存修改</button>--%>
                    </form>
                </div>
                <div class="profile-section" id="avatarSection" style="display:none;">
                    <h3>修改头像</h3>
                    <form action="UploadServlet" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="uploadType" value="avatar">
                        <div class="form-group">
                            <label>选择新头像</label>
                            <input type="file" class="form-control" name="avatar"
                                   accept="image/*" onchange="previewImage(this, 'avatarPreview')">
                        </div>
                        <div class="preview-container">
                                <img id="avatarPreview" class="upload-preview" style="display:none;">
                        </div>
                        <button type="submit" class="btn btn-primary btn-upload">上传头像</button>
                    </form>
                </div>
                <!-- 商品管理部分 -->
                <div class="profile-section" id="productsSection" style="display:none;">
                    <h3>商品管理</h3>
                    <form action="UploadServlet" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="uploadType" value="product">
                        <div class="form-group">
                            <label>蛋糕名称</label>
                            <input type="text" class="form-control" name="productName" required>
                        </div>
                        <div class="form-group">
                            <label>蛋糕图片</label>
                            <input type="file" class="form-control" name="productImage"
                                   accept="image/*" onchange="previewImage(this, 'productPreview')" required>
                        </div>
                        <img id="productPreview" class="upload-preview" style="display:none;">
                        <div class="form-group">
                            <label>蛋糕描述</label>
                            <textarea class="form-control" name="productDescription" rows="3" required></textarea>
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input type="number" class="form-control" name="productPrice" step="0.01" required>
                        </div>
                        <button type="submit" class="btn btn-primary">上传商品</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script>
        function previewImage(input, previewId) {
            const preview = document.getElementById(previewId);
            if (input.files && input.files[0]) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    preview.src = e.target.result;
                    preview.style.display = 'block';
                }
                reader.readAsDataURL(input.files[0]);
            }
        }
        // // 切换不同的内容区域
        document.addEventListener('DOMContentLoaded', function() {
            // 获取所有导航链接
            const navLinks = document.querySelectorAll('.list-group-item');
            // 获取所有内容区域
            const sections = document.querySelectorAll('.profile-section');

            // 为每个导航链接添加点击事件
            navLinks.forEach(link => {
                link.addEventListener('click', function(e) {
                    e.preventDefault(); // 阻止默认的链接行为

                    // 移除所有导航项的active类
                    navLinks.forEach(item => item.classList.remove('active'));
                    // 为当前点击的导航项添加active类
                    this.classList.add('active');

                    // 获取目标section的ID
                    const targetId = this.getAttribute('data-section') + 'Section';

                    // 隐藏所有section
                    sections.forEach(section => {
                        if (section.id) {  // 确保元素有id
                            section.style.display = 'none';
                        }
                    });

                    // 显示目标section
                    const targetSection = document.getElementById(targetId);
                    if (targetSection) {
                        targetSection.style.display = 'block';
                    }
                });
            });
        });
    </script>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
