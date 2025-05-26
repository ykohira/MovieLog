<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${movie.title}の詳細</title>
<%@ include file="header.jsp"%>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="/movieLog/css/style.css">
</head>
<body>
	<div class="movie-detail-container">
		<div class="poster">
			<img src="${movie.thumbnailUrl}" alt="ポスター" />
		</div>
		<div class="movie-info">
			<h1>${movie.title}</h1>
			<p>
				<strong>ジャンル：</strong>${movie.genre}</p>
			<p>
				<strong>あらすじ：</strong>${movie.description}</p>

			<div class="bottom-right">
				<form action="/movieLog/addToLibrary" method="get">
					<input type="hidden" name="title" value="${movie.title}" /> <input
						type="hidden" name="genre" value="${movie.genre}" /> <input
						type="hidden" name="description" value="${movie.description}" />
					<input type="hidden" name="tmdb_id" value="${movie.tmdbId}" /> <input
						type="hidden" name="thumbnail_url" value="${movie.thumbnailUrl}" />


					<!-- ライブラリの状態に応じてボタン表示 -->
					<c:choose>
						<c:when test="${alreadyInLibrary}">
							<button type="button" disabled class="btn library">ライブラリ追加済み</button>
						</c:when>
						<c:otherwise>
							<button type="submit" name="action" value="library"
								class="btn library">ライブラリに追加</button>
						</c:otherwise>
					</c:choose>

					<!-- ウォッチリストの状態に応じてボタン表示 -->
					<c:choose>
						<c:when test="${alreadyInWatchlist}">
							<button type="button" disabled class="btn watchlist">ウォッチリスト済み</button>
						</c:when>
						<c:otherwise>
							<button type="submit" name="action" value="watchlist"
								class="btn watchlist">ウォッチリストに追加</button>
						</c:otherwise>
					</c:choose>

				</form>

			</div>
		</div>
	</div>
	<p style="margin-top: 20px;">
		<a href="/movieLog/jsp/search.jsp">← 検索に戻る</a>
	</p>
</body>
</html>
