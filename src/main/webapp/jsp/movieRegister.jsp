<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>作品登録画面</title>
<%@ include file="header.jsp"%>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="/movieLog/css/style.css">
</head>
<body>
		<h1>映画・ドラマの登録</h1>


	<div class="content">
		<form action="movieRegisterDone.jsp" method="post">
			<div class="form-group">
				<label for="genre">ジャンル</label> <select id="genre" name="genre">
					<option value="action">アクション</option>
					<option value="drama">ドラマ</option>
					<option value="comedy">コメディ</option>
					<!-- 他のジャンルを追加 -->
				</select>
			</div>
			<div class="form-group">
				<label for="tags">タグ</label> <input type="text" id="tags"
					name="tags" placeholder="カンマ区切りで入力">
			</div>
			<button type="submit">登録</button>
		</form>
	</div>
</body>
</html>