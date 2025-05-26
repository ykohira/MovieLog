<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>ログイン | 映画アプリ</title>
<link rel="stylesheet" href="/movieLog/css/form.css">
</head>
<body>
	<div class="form-container">
		<div class="form-wrapper">
			<form action="/movieLog/login" method="post" class="login-form">
				<h2>ログイン</h2>

				<!-- ログイン失敗時のエラーメッセージ表示 -->
				<!-- testはライブラリに入っているタグ -->
				<c:if test="${loginError != null}">
					<div style="color: red;">
						<p>${loginError}</p>
					</div>
				</c:if>
				<div class="form-group">
					<label for="username">ユーザー名</label> <input type="text"
						id="username" name="userName" required>
				</div>
				<div class="form-group">
					<label for="password">パスワード</label> <input type="password"
						id="password" name="password" required>
				</div>
				<button type="submit">ログイン</button>
			</form>
			<p class="form-link">
				<a href="/movieLog/jsp/register.jsp">新規会員登録はこちら</a>
			</p>

		</div>
	</div>

</body>
</html>
