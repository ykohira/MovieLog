<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録削除</title>
</head>
<body>
		<h1>映画・ドラマの削除</h1>

	<div class="content">
		<p>本当にこの映画・ドラマを削除しますか？</p>
		<form action="movieDeleteDone.jsp" method="post">
			<input type="hidden" name="movieId"
				value="<%=request.getAttribute("movieId")%>">
			<button type="submit">削除する</button>
		</form>
		<form action="library.jsp">
			<button type="submit">キャンセル</button>
		</form>
	</div>
</body>
</html>