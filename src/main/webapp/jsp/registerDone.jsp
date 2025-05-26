<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録完了画面</title>
<%@ include file="header.jsp"%>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="/movieLog/css/style.css">
</head>
<body>
		<h1>登録完了</h1>

	<div class="content">
		<p>新規登録が完了しました！</p>
		<form action="/movieLog/login" method="post">
			<input type="hidden" name="userName" value="${user.userName}">
			<input type="hidden" name="password" value="${user.password}">
			<button type="submit">マイページへ</button>
		</form>
	</div>
</body>
</html>