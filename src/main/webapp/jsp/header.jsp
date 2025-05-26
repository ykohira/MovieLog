<%@ page session="true" contentType="text/html; charset=UTF-8"
	language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>共通ヘッダー</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>

	<c:set var="user" value="${sessionScope.user}" />

	<div class="header">
		<!-- 左：ロゴ -->
		<div class="logo-container">
			<a href="${pageContext.request.contextPath}/login" class="logo">
				<img src="${pageContext.request.contextPath}/images/homeicon.svg"
				alt="ホーム" width="32" height="32">
			</a>
		</div>

		<!-- 右：ウェルカム＋トグル＋ドロップダウン -->
		<div class="menu-container">
			<c:if test="${not empty user}">
				<span class="welcome">${user.userName} 様 ログイン中</span>
			</c:if>
			<button class="menu-toggle" onclick="toggleMenu()">
				<span class="bar"></span> <span class="bar"></span> <span
					class="bar"></span>
			</button>



			<nav class="dropdown-menu" id="dropdownMenu">
				<a href="${pageContext.request.contextPath}/jsp/search.jsp">検索</a> <a
					href="${pageContext.request.contextPath}/tagList">タグ一覧</a> <a
					href="${pageContext.request.contextPath}/library">ライブラリ</a> <a
					href="${pageContext.request.contextPath}/watchlist">ウォッチリスト</a> <a
					href="${pageContext.request.contextPath}/jsp/edit.jsp">会員情報変更</a> <a
					href="${pageContext.request.contextPath}/delete">退会</a> <a
					href="${pageContext.request.contextPath}/logout">ログアウト</a>
			</nav>
		</div>
	</div>

	<script>
		function toggleMenu() {
			const menu = document.getElementById('dropdownMenu');
			menu.classList.toggle('show');
		}

		// メニューの外をクリックしたら閉じる
		document.addEventListener('click', function(event) {
			const menu = document.getElementById('dropdownMenu');
			const toggleButton = document.querySelector('.menu-toggle');

			// クリック対象がメニューやトグルボタンじゃないなら閉じる
			if (!menu.contains(event.target)
					&& !toggleButton.contains(event.target)) {
				menu.classList.remove('show');
			}
		});
	</script>


</body>
</html>
