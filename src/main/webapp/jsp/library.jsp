<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*, dto.LibraryDTO"%>
<html>
<head>
<title>ライブラリ</title>

<link rel="stylesheet" href="/css/header.css">
<link rel="stylesheet" href="/movieLog/css/style.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<h1 style="text-align: center; margin-top: 40px;">あなたの映画・ドラマライブラリ</h1>

	<!-- ジャンルフィルター追加 -->
	<select id="genreSelect">
		<option value="">すべてのジャンル</option>
		<%
		// ジャンルをリストで渡す例
		List<String> genres = Arrays.asList("Action", "Adventure", "Animation", "Comedy", "Crime", "Documentary", "Drama",
				"Family", "Fantasy", "History", "Horror", "Music", "Mystery", "Romance", "Science Fiction", "TV Movie",
				"Thriller", "War", "Western");
		for (String genre : genres) {
		%>
		<option value="<%=genre%>"><%=genre%></option>
		<%
		}
		%>
	</select>

	<%
	List<LibraryDTO> movieList = (List<LibraryDTO>) request.getAttribute("movieList");
	if (movieList != null && !movieList.isEmpty()) {
	%>
	<div class="movie-grid">
		<%
		for (LibraryDTO movie : movieList) {
		%>
		<!-- 各映画のジャンルを data-genre に設定 -->
		<div class="movie-card" data-genre="<%=movie.getGenre()%>">
			<a href="/movieLog/libraryDetail?movie_id=<%=movie.getMovieId()%>"
				class="poster-link"> <img src="<%=movie.getThumbnailUrl()%>"
				alt="ポスター" class="library-poster">
				<div class="hover-title"><%=movie.getTitle()%></div>
			</a>
		</div>
		<%
		}
		%>
	</div>
	<%
	} else {
	%>
	<p>まだライブラリに映画が登録されていません。</p>
	<%
	}
	%>

	<!-- JavaScriptでジャンルフィルタリング -->
	<script>
	document.getElementById("genreSelect").addEventListener("change", function () {
	    const selectedGenre = this.value.toLowerCase().trim();
	    document.querySelectorAll(".movie-card").forEach(card => {
	        const genre = (card.dataset.genre || "").toLowerCase().trim();
	        // 選択したジャンルが空、または一致する場合だけ表示
	        if (selectedGenre === "" || genre === selectedGenre) {
	            card.style.display = ""; // 初期値に戻す（display: gridにより自動整列される）
	        } else {
	            card.style.display = "none"; // 非表示
	        }
	    });
	});


	// アイテム数に応じて最大幅を調整する処理
window.addEventListener('DOMContentLoaded', updateGridWidth);
document.getElementById("genreSelect").addEventListener("change", function () {
    const selectedGenre = this.value.toLowerCase().trim();
    document.querySelectorAll(".movie-card").forEach(card => {
        const genre = (card.dataset.genre || "").toLowerCase().trim();
        if (selectedGenre === "" || genre === selectedGenre) {
            card.style.display = ""; // 表示
        } else {
            card.style.display = "none"; // 非表示
        }
    });
    updateGridWidth(); // フィルタ後に再判定！
});

function updateGridWidth() {
    const movieGrid = document.querySelector('.movie-grid');
    if (!movieGrid) return;

    const visibleCards = Array.from(movieGrid.querySelectorAll('.movie-card'))
        .filter(card => card.style.display !== 'none');

    if (visibleCards.length <= 5) {
        movieGrid.classList.add('fixed-width');
        movieGrid.classList.remove('auto-width');
    } else {
        movieGrid.classList.add('auto-width');
        movieGrid.classList.remove('fixed-width');
    }
}


	</script>

</body>
</html>
