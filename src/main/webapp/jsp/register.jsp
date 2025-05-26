<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規会員登録</title>
<link rel="stylesheet" href="/movieLog/css/form.css">
</head>

<body>
	<div class="form-container">
		<div class="form-wrapper">

			<form action="/movieLog/register" method="post" class="register-form">
				<h2>新規登録</h2>
				<!-- ログイン失敗時のエラーメッセージ表示 -->
				<!-- testはライブラリに入っているタグ -->
				<c:if test="${errorMsg != null}">
					<div style="color: red;">
						<p>${errorMsg}</p>
					</div>
				</c:if>
				<div class="form-group">
					<label for="name">ユーザー名</label> <input type="text" id="name"
						name="userName" required>
				</div>
				<!-- 余裕があったら追加する。コントローラー等も調整する必要あり -->
				<!-- 		<div class="form-group">
				<label for="email">メールアドレス</label> <input type="email" id="email"
					name="email" required>
			</div> -->
				<div class="form-group">
					<label for="password">パスワード</label> <input type="password"
						id="password" name="password" required>
				</div>
				<button type="submit">確認</button>
			</form>
			<p class="form-link">
				<a href="/movieLog/jsp/top.jsp">戻る</a>
			</p>
		</div>
	</div>
</body>
</html>