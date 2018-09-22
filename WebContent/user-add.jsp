<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>添加用户</title>
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">		
		<link rel="stylesheet" type="text/css" href="css/reset.css"/>
		<link rel="stylesheet" type="text/css" href="css/index.css"/>
		<style type="text/css">
		.user-add{font-size: 24px;margin-top: 80px;padding-left: 0px;box-sizing: border-box;color: #333333;margin-bottom: 50px;}
		</style>
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
					首页
					<div class="index-nav-frame-line-focus" tabindex="-1"></div>
				</div>
				<div class="index-nav-frame-line" tabindex="-1">
					用户列表
					<div class="index-nav-frame-line-center">
						<div class="index-nav-frame-line-li">
							用户列表
						</div>	
					</div>
					<div class="index-nav-frame-line-focus" tabindex="-1"></div>
				</div>
				<a href="LogoutServlet" class="index-nav-frame-line-logout" tabindex="-1">退出</a>
			</div>
		</div>
		<div class="index-content">
			<div class="index-content-operation">
				<a class="user-add" >添加用户</a> 
				<br>
				<br>
				<br>
				<br>	
			</div>
			<br>
			<form action="UserServlet?operation=add" method="post" onsubmit="return check()">
				<table class="index-content-table-add">
					<tr>
						<td>账户：<input class="index-content-table-td-add" type="text" id="username" name="username" value=""/></td>
					</tr>
					<tr>
						<td>密码：<input class="index-content-table-td-add" type="password" id="password"  name="password" value=""/></td>
					</tr>
					<tr>
						<td>姓名：<input class="index-content-table-td-add" type="text" id="realname" name="realname" value=""/></td>
					</tr>
					<tr>
						<td>性别：&nbsp;&nbsp;&nbsp;&nbsp;<input name="sex" type="radio" value="true" checked="checked"/>&nbsp;&nbsp;&nbsp;男&nbsp;&nbsp;&nbsp;&nbsp;<input name="sex" value="false" type="radio" />&nbsp;&nbsp;&nbsp;女</td>
					</tr>
					<tr>
						<td>地址：<input class="index-content-table-td-add" type="text" id="address" name="address" value=""/></td>
					</tr>
				</table>
				<br>
				<br>
				<br>	
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="submit" class="index-content-operation-add">提交</button>&nbsp;&nbsp;&nbsp;<button type="button" class="index-content-operation-add" onclick="javascript:history.back(-1);">取消</button>
			</form>
		</div>

	</body>
<script type="text/javascript">
//提交之前进行检查，如果return false，则不允许提交
function check(){
//根据ID获取值
var username=document.getElementById("username").value;
var password=document.getElementById("password").value;
var realname=document.getElementById("realname").value;
var address=document.getElementById("address").value;
if(username==""){
alert("用户名不能为空");
return false;

}
if(password==""){
alert("密码不能为空");
return false;
}
if(realname==""){
	alert("姓名为空");
	return false;
	}
if(address==""){
	alert("地址不能为空");
	return false;
	}

return true;
}
</script>
</html>
