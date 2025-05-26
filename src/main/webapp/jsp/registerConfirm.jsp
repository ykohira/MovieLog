<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title>登録確認</title>
<link rel="stylesheet" href="/movieLog/css/form.css">
</head>
<body>

	<div class="form-container">
		<div class="form-wrapper">
			<form action="/movieLog/registerConfirm" method="post" class="register-form">
				<h2>登録確認</h2>
				<div class="form-group">
					<p>
						名前: <span>${user.userName}</span> <input type="hidden"
							name="userName" value="${user.userName}">
					</p>
				</div>
				<div class="form-group">
					<p>
						パスワード: <span>${user.password}</span> <input type="hidden"
							name="password" value="${user.password}">
					</p>
				</div>
				<button type="submit">登録</button>
			</form>
			<p>
				<a href="/movieLog/jsp/register.jsp">修正する</a>
			</p>
		</div>
	</div>
</body>
</html>
