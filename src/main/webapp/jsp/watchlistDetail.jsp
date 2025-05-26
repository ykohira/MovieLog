<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${movie.title}の詳細</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<%@ include file="header.jsp"%>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="/movieLog/css/style.css">
</head>
<body>
	<p style="margin-top: 20px;">
		<a href="/movieLog/watchlist">← ウォッチリストに戻る</a>
	</p>
	<div class="movie-detail-container">
		<div class="poster">
			<img src="${movie.thumbnailUrl}" alt="ポスター">
		</div>

		<div class="movie-info">
			<div class="tags mb-1">
				<c:if test="${not empty tagList}">
					<p>
						<c:forEach var="tag" items="${tagList}" varStatus="status">
							<span class="badge"
								style="background-color: #ff6600; color: white;"me-2">${tag.name}</span>
							<c:if test="${!status.last}">
							</c:if>
						</c:forEach>
					</p>
				</c:if>
			</div>
			<h1>${movie.title}</h1>
			<p>ジャンル：${movie.genre}</p>
			<p>あらすじ：${movie.description}</p>

			<div class="bottom-right">
				<a href="/movieLog/watchlistEdit?id=${movie.id}" id="link">編集</a>
			</div>
		</div>
	</div>

</body>
</html>
