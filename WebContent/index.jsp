<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>主页</title>
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">		
		<link rel="stylesheet" type="text/css" href="css/reset.css"/>
		<link rel="stylesheet" type="text/css" href="css/index.css"/>
	</head>
	<body style="background: #ebebeb;">
		<div class="index-nav">
			<div class="index-nav-frame clearfix">
				<div class="nav-line">	
				</div>
				<div class="nav-small" tabindex="-1">
					<div class="nav-small-focus" tabindex="-1">	
					</div>
					<img src="img/icon.png"/>
				</div>
				<div class="index-nav-frame-line active" tabindex="-1">
					<a href="UserServlet">首页</a>
					<div class="index-nav-frame-line-focus" tabindex="-1"></div>
				</div>
				<div class="index-nav-frame-line" tabindex="-1">
					用户管理
					<div class="index-nav-frame-line-center">
						<div class="index-nav-frame-line-li">
							<a style="background:white;color: #1E90FF" href="UserServlet">用户列表</a>
						</div>	
					</div>
					<div class="index-nav-frame-line-focus" tabindex="-1"></div>
				</div>
				<a href="LogoutServlet" class="index-nav-frame-line-logout" tabindex="-1">退出</a>
			</div>
		</div>
		<div class="index-content">
			<div class="index-content-operation">
				<button class="index-content-operation-add" onclick="window.location.href='user-add.jsp'">添加</button> 
				<div class="index-content-operation-search"><input id="search_username" placeholder="请输入用户名关键字" type="text" name="username"/><button onclick="searchUser()">搜索</button></div>
			</div>
			<br>
			<table class="index-content-table">
				<tr class="index-content-table-th">
				<th>用户名</th>
				<th>密码</th>
				<th>姓名</th>
				<th>性别</th>
				<th>地址</th>
				<th>操作</th>
				</tr>
				<c:forEach items="${userList}" var="ul">
					<tr class="index-content-table-td">
					<td>${ul.username}</td>
					<td>${ul.password}</td>
					<td>${ul.realname}</td>
					<td>
						<c:if test="${true==ul.sex}">男</c:if>
						<c:if test="${false==ul.sex}">女</c:if>
					</td>
					<td>${ul.address}</td>
					<td><a class="index-content-table-td-edit" href='GetEditUserServlet?edit_id=${ul.id}'>编辑</a>&nbsp; &nbsp; &nbsp; <a class="index-content-table-td-delete" onclick='return window.confirm("将要删除：${ul.username}")' href='UserServlet?operation=delete&id=${ul.id}'>删除</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</body>
<script>
function searchUser(){
	var search_username = document.getElementById("search_username").value;
	window.location.href = "UserServlet?operation=search&username=" + search_username;
}
</script>
</html>
