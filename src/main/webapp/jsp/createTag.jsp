<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タグ作成</title>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="/movieLog/css/style.css">
</head>
<body>
	<%@ include file="header.jsp"%>

	<%-- エラーメッセージの表示 --%>
	<c:if test="${not empty errorMessage}">
		<div style="color: red;">${errorMessage}</div>
	</c:if>


	<div class="content">
		<form action="/movieLog/createTag" method="POST" accept-charset="UTF-8">
			<label for="tagName">タグ名:</label> <input type="text" id="tagName"
				name="tagName" required />
			<button type="submit">タグを作成</button>
		</form>
	</div>

</body>
</html>