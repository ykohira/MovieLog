<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<h1>タグ・ジャンルの追加・編集</h1>
	</header>

	<div class="content">
		<form action="genreEditDone.jsp" method="post">
			<div class="form-group">
				<label for="newGenre">新しいジャンル</label> <input type="text"
					id="newGenre" name="newGenre" required>
			</div>
			<button type="submit">追加</button>
		</form>

		<h2>既存のタグ・ジャンル</h2>
		<ul>
			<li>アクション</li>
			<li>ドラマ</li>
			<li>コメディ</li>
			<!-- 他のジャンルをリストとして表示 -->
		</ul>
	</div>
</body>
</html>