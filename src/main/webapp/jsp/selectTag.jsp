<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タグを選択してください</title>
<style>
/* ラベルの外見 */
/* ラベルのデザイン */
.tag-checkbox label {
	display: inline-block;
	background: #fff;
	border: 1px solid #ccc;
	border-radius: 20px;
	padding: 8px 14px;
	margin: 6px 6px 6px 0;
	cursor: pointer;
	transition: background 0.3s, color 0.3s, border-color 0.3s;
}

/* inputは非表示 */
.tag-checkbox input[type="checkbox"] {
	display: none;
}

/* チェックされたときのラベルをスタイル変更 */
.tag-checkbox input[type="checkbox"]:checked+label {
	background: orange;
	color: white;
	border-color: orange;
}

.new-tag input[type="text"] {
	width: 100%;
	padding: 10px 12px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: 6px;
	margin-top: 8px;
}
</style>
<link rel="stylesheet" href="/movieLog/css/style.css">
</head>
<body>
	<div class="tag-select-container">
		<h2>タグを選んで登録（任意）</h2>

		<form action="/movieLog/addToLibrary" method="post">
			<!-- 映画情報のhidden保持 -->
			<input type="hidden" name="title" value="${param.title}" /> <input
				type="hidden" name="genre" value="${param.genre}" /> <input
				type="hidden" name="description" value="${param.description}" /> <input
				type="hidden" name="tmdb_id" value="${param.tmdb_id}" /> <input
				type="hidden" name="thumbnail_url" value="${param.thumbnail_url}" />
			<input type="hidden" name="action" value="${param.action}" />

			<!-- 既存のタグ一覧をチェックボックスで表示 -->
			<!-- 既存タグ -->
			<div class="existing-tags tag-checkbox">
				<p>
					<strong>既存タグ：</strong>
				</p>
				<c:forEach var="tag" items="${tagList}">
					<input type="checkbox" id="tag-${tag.name}" name="selectedTags"
						value="${tag.name}" />
					<label for="tag-${tag.name}">${tag.name}</label>
				</c:forEach>
			</div>


			<!-- 新規タグ入力 -->
			<div class="new-tag">
				<p>
					<strong>新しいタグを追加：</strong>
				</p>
				<input type="text" name="newTag" placeholder="例：感動、アクション" />
			</div>

			<!-- ボタン -->
			<div class="buttons">
				<button type="submit" name="submitType" value="withTags" class="btn">登録</button>
				<button type="submit" name="submitType" value="skipTags" class="btn">スキップして登録</button>
			</div>
		</form>

		<p>
			<a href="/movieLog/detail?tmdbId=${param.tmdb_id}" id="link">キャンセル</a>
		</p>
	</div>
</body>
</html>
