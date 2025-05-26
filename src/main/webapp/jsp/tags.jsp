<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="dto.TagDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タグ一覧</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/header.css">
<!-- Bootstrap CDN追加 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- 自作CSS（最後に読み込む） -->
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/header.css">

</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="container mt-5">

	<h2 class="text-center mb-4">タグ一覧</h2>

	<c:if test="${not empty successMessage}">
		<div class="alert alert-success" role="alert">
			${successMessage}
		</div>
	</c:if>

	<c:if test="${not empty errorMessage}">
		<div class="alert alert-danger" role="alert">
			${errorMessage}
		</div>
	</c:if>

	<div class="mb-3 text-end">
		<a href="/movieLog/jsp/createTag.jsp" class="btn btn-primary">＋ 新しいタグを作成</a>
	</div>

	<ul class="list-group">
		<!-- ELを使用してtagListをループ -->
		<c:forEach var="tag" items="${tagList}">
			<li class="list-group-item d-flex justify-content-between align-items-center">
				${tag.name}
				<form action="/movieLog/deleteTag" method="POST" onsubmit="return confirm('本当に削除しますか？');">
					<input type="hidden" name="tagId" value="${tag.id}">
					<button type="submit" class="btn btn-sm btn-outline-danger">削除</button>
				</form>
			</li>
		</c:forEach>

		<!-- tagListが空の場合 -->
		<c:if test="${empty tagList}">
			<li class="list-group-item">タグがまだ登録されていません。</li>
		</c:if>
	</ul>

</div>

</body>
</html>
