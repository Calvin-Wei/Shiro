<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<% String basePath=request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>shiro的登录界面</title>
</head>
<body>
	<h1>简易登录界面</h1>
	<form action="<%=basePath%>/login/login.action" method="post">
		username:<input type="text" name="username" />
		<p>
			password:<input type="password" name="password" /> ${msg} <input
				type="submit" value="submit" />
	</form>
</body>
</html>