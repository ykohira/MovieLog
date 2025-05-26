<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録作品編集画面</title>
<%@ include file="header.jsp"%>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="/movieLog/css/style.css">
</head>
<body>
	<p style="margin-top: 20px;">
		<a href="/movieLog/libraryDetail?movie_id=${movie.movieId}">← 詳細に戻る</a>
	</p>
	<div class="movie-detail-container">
		<div class="poster">
			<img src="${movie.thumbnailUrl}" alt="ポスター" width="300px" />
		</div>
		<div class="movie-info">
			<h1>${movie.title}</h1>
			<p>ジャンル：${movie.genre}</p>
			<p>あらすじ：${movie.description}</p>

			<div class="bottom-right">
				<!-- 削除ボタン（削除処理用サーブレットにPOST） -->
				<form action="/movieLog/deleteMovie" method="post">
					<input type="hidden" name="movie_id" value="${movie.movieId}">
					<button type="submit">この映画を削除</button>
				</form>
			</div>
		</div>
	</div>


	<%-- 	<div class="content">
		<form action="/libraryEdit" method="post">
			<div class="form-group">
				<label for="title">タイトル</label> <input type="text" id="title"
					name="title" value="<%=request.getAttribute("title")%>" required>
			</div>
			<div class="form-group">
				<label for="tags">タグ</label> <input type="text" id="tags"
					name="tags" value="<%=request.getAttribute("tags")%>"
					placeholder="カンマ区切りで入力">
			</div>
			<button type="submit">変更を保存</button>
		</form> --%>
	<!-- </div> -->
</body>
</html>