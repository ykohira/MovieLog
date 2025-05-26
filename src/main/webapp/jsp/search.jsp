<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索画面</title>

<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="/movieLog/css/style.css">
</head>
<body>
<%@ include file="header.jsp" %>
	<h1>映画・ドラマを検索</h1>
	<div class="content">
		<form action="/movieLog/search" method="GET">
			<div class="form-group">
				<label for="query">検索キーワード</label> <input type="text" id="query"
					name="query" required>
			</div>
			<div class="form-group">
				<label for="genre">ジャンル</label> <select id="genre" name="genre">
					<option value="">全て</option>
					<option value="action">アクション</option>
					<option value="drama">ドラマ</option>
					<option value="comedy">コメディ</option>
					<!-- 他のジャンルを追加 -->
				</select>
			</div>
			<button type="submit">検索</button>
		</form>
	</div>
</body>
</html>