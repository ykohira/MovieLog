<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報編集画面</title>
<%-- <%@ include file="header.jsp"%>
<link rel="stylesheet" href="css/header.css"> --%>
<link rel="stylesheet" href="/movieLog/css/form.css">
</head>

<body>

	<div class="form-container">
		<div class="form-wrapper">

			<form action="/movieLog/edit" method="post" class="edit-form">
				<h2>変更内容入力</h2>
				<!-- 入力値チェック時のエラーメッセージ表示 -->
				<c:if test="${errorMsg.size() > 0 }">
					<ul>
						<c:forEach var="msg" items="${errorMsg}">
							<li style="color: red;">${msg}</li>
						</c:forEach>
					</ul>
				</c:if>

				<!-- 更新失敗時のエラーメッセージ -->
				<c:if test="${editError != null}">
					<div style="color: red;">
						<p>${editError}</p>
					</div>
				</c:if>
				<div class="form-group">
					<label for="username">ユーザー名</label> <input type="text"
						id="username" name="newUserName" placeholder="1～10文字">
				</div>
				<div class="form-group">
					<label for="password">パスワード</label> <input type="password"
						id="password" name="newPassword" placeholder="2～10文字">
				</div>
				<button type="submit">変更する (確認画面へ)</button>
			</form>
			<p>
				<a href="/movieLog/jsp/home.jsp">戻る</a>
			</p>
		</div>
	</div>

</body>
</html>