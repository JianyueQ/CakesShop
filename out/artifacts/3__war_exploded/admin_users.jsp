<%--
  Created by IntelliJ IDEA.
  User: yuxia
  Date: 2024/12/19
  Time: 下午11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2>用户管理</h2>

    <!-- 搜索框 -->
    <div class="search-box">
        <form action="AdminUserServlet" method="get" class="form-inline mb-3">
            <input type="hidden" name="action" value="search">
            <input type="text" name="searchUsername" class="form-control mr-2" placeholder="输入用户名搜索">
            <button type="submit" class="btn btn-primary">搜索</button>
        </form>
    </div>

    <!-- 用户列表 -->
    <table class="table table-striped">
        <thead>
        <tr>
            <th>用户名</th>
            <th>邮箱</th>
            <th>电话</th>
            <th>姓名</th>
            <th>地址</th>
            <th>角色</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.phone}</td>
                <td>${user.name}</td>
                <td>${user.address}</td>
                <td>${user.role}</td>
                <td>
                    <button onclick="editUser('${user.username}')" class="btn btn-primary btn-sm">编辑</button>
                    <button onclick="deleteUser('${user.username}')" class="btn btn-danger btn-sm">删除</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- 编辑用户的模态框 -->
<div class="modal fade" id="editUserModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">编辑用户</h5>
                <button type="button" class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="editUserForm">
                    <div class="form-group">
                        <label>用户名</label>
                        <input type="text" class="form-control" id="editUsername" name="username" readonly>
                    </div>
                    <div class="form-group">
                        <label>邮箱</label>
                        <input type="email" class="form-control" id="editEmail" name="email">
                    </div>
                    <div class="form-group">
                        <label>电话</label>
                        <input type="text" class="form-control" id="editPhone" name="phone">
                    </div>
                    <div class="form-group">
                        <label>姓名</label>
                        <input type="text" class="form-control" id="editName" name="name">
                    </div>
                    <div class="form-group">
                        <label>地址</label>
                        <input type="text" class="form-control" id="editAddress" name="address">
                    </div>
                    <div class="form-group">
                        <label>角色</label>
                        <select class="form-control" id="editRole" name="role">
                            <option value="消费者">消费者</option>
                            <option value="商家">商家</option>
<%--                            <option value="管理员">管理员</option>--%>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="saveUserEdit()">保存</button>
            </div>
        </div>
    </div>
</div>

<script>
    function editUser(username) {
        $.get('AdminUserServlet?action=getUser&username=' + username, function(user) {
            $('#editUsername').val(user.username);
            $('#editEmail').val(user.email);
            $('#editPhone').val(user.phone);
            $('#editName').val(user.name);
            $('#editAddress').val(user.address);
            $('#editRole').val(user.role);
            $('#editUserModal').modal('show');
        });
    }

    function saveUserEdit() {
        $.post('AdminUserServlet?action=update', $('#editUserForm').serialize(), function(response) {
            if (response.success) {
                alert('更新成功');
                location.reload();
            } else {
                alert('更新失败');
            }
        });
    }

    function deleteUser(username) {
        if (confirm('确定要删除用户 ' + username + ' 吗？')) {
            $.post('AdminUserServlet?action=delete&username=' + username, function(response) {
                if (response.success) {
                    alert('删除成功');
                    location.reload();
                } else {
                    alert('删除失败');
                }
            });
        }
    }
</script>
</body>
</html>