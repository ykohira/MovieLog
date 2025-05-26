<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>マイページ</title>

	<link rel="stylesheet" href="css/header.css"> <!-- ヘッダーのCSS -->
	<link rel="stylesheet" href="/movieLog/css/home.css"> <!-- メインコンテンツのCSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="container mt-3">
		<h5 class="mb-2">ライブラリ</h5>
		<div class="movie-scroll-container">
			<c:forEach var="movie" items="${uMovieList}">
				<div class="card movie-card">
					<img src="${movie.thumbnailUrl}" class="card-img-top" alt="${movie.title}">
				</div>
			</c:forEach>
		</div>
	</div>
	
	<div class="container mt-3">
		<h5 class="mb-2">ウォッチリスト</h5>
		<div class="movie-scroll-container">
			<c:forEach var="watchlist" items="${uWatchlist}">
				<div class="card movie-card">
					<img src="${watchlist.thumbnailUrl}" class="card-img-top" alt="${watchlist.title}">
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>
