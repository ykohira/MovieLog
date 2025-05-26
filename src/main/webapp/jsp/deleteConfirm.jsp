<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>退会確認画面</title>
<%@ include file="header.jsp" %>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="/movieLog/css/style.css">
</head>
<body>
		<h1>退会確認</h1>

	<c:if test="${deleteError != null}">
		<div style="color: red;">
			<p>${deleteError}</p>
		</div>
	</c:if>

	<div class="content">
		<p>下記の内容でよろしければ、再度パスワードを入力し退会ボタンを押してください。</p>
		<form action="/movieLog/delete" method="post" class="delete">
			<table border="1">
				<tr>
					<th>ユーザー名</th>
					<td><input type="hidden" name="deleteUserName"
						value="${user.userName}">${user.userName}</td>
				</tr>
				<tr>
					<th>パスワードを入力してください</th>
					<td><input type="password" name="password"></td>
				</tr>
			</table>
			<button type="submit">退会</button>
		</form>
	</div>
</body>
</html>