<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索結果</title>
<%@ include file="header.jsp"%>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="/movieLog/css/style.css">
</head>
<body>
	<h1>「${keyword}」の検索結果</h1>

	<p>
		<a href="/movieLog/jsp/search.jsp">←検索画面に戻る</a>
	</p>

	<c:if test="${empty movieList}">
		<p>該当する映画が見つかりませんでした。</p>
	</c:if>

	<c:forEach var="movie" items="${movieList}" varStatus="status">
		<div style="margin: 10px; display: inline-block;">
			<a href="/movieLog/detail?tmdbId=${movie.tmdbId}"> <img
				src="${movie.thumbnailUrl}" alt="ポスター" width="150px" />
			</a>

		</div>
	</c:forEach>




	<!-- <script>
        function toggleDetails(index) {
            const detail = document.getElementById(`details-${index}`);
            detail.style.display = (detail.style.display === "none" || detail.style.display === "") ? "block" : "none";
        }
    </script> -->
</body>
</html>
