<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報変更確認画面</title>
<link rel="stylesheet" href="/movieLog/css/form.css">
</head>

<body>

	<div class="form-container">
		<div class="form-wrapper">
			<form action="/movieLog/editConfirm" method="post" class="edit-form">
				<h2>変更内容確認</h2>
				<p>下記の内容でよろしければ、変更ボタンを押してください。</p>

				<div class="form-group">
					<p>
						名前: <span>${newUserName}</span> <input type="hidden"
							name="newUserName" value="${newUserName}">
					</p>
				</div>
				<div class="form-group">
					<p>
						パスワード: <span>${newPassword}</span> <input type="hidden"
							name="newPassword" value="${newPassword}">
					</p>
				</div>
				<button type="submit">変更する</button>
			</form>

			<p>
				<a href="/movieLog/jsp/edit.jsp">修正する</a>
			</p>

		</div>
	</div>

</body>
</html>