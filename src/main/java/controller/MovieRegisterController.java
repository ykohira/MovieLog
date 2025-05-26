package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TagDAO;
import domain.User;
import dto.MovieDTO;
import dto.TagDTO;
import service.MovieRegisterService;

@WebServlet("/addToLibrary")
public class MovieRegisterController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// ログインしていなければログインページへ
		if (user == null) {
			response.sendRedirect("/movieLog/jsp/login.jsp");
			return;
		}

		// 必要なパラメータを取得
		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		String description = request.getParameter("description");
		String thumbnailUrl = request.getParameter("thumbnail_url");
		String action = request.getParameter("action");
		int tmdbId = Integer.parseInt(request.getParameter("tmdb_id"));

		// 映画DTOを作ってセッションへ一時保存（タグ選択時に使う）
		MovieDTO movie = new MovieDTO();
		movie.setTitle(title);
		movie.setGenre(genre);
		movie.setDescription(description);
		movie.setThumbnailUrl(thumbnailUrl);
		movie.setTmdbId(tmdbId);

		session.setAttribute("selectedMovie", movie);
		session.setAttribute("addAction", action); // "library" or "watchlist"

		// タグ一覧取得
		TagDAO tagDAO = null;
		try {
			tagDAO = new TagDAO();
		} catch (SQLException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		List<TagDTO> tagList = null;
		try {
			tagList = tagDAO.getAllTags(user.getId());
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		request.setAttribute("tagList", tagList);
		request.getRequestDispatcher("/jsp/selectTag.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user == null) {
			response.sendRedirect("/movieLog/jsp/login.jsp");
			return;
		}

		// セッションから必要データを取得
		MovieDTO movie = (MovieDTO) session.getAttribute("selectedMovie");
		String action = (String) session.getAttribute("addAction");

		// 1. チェックボックスで選ばれた既存タグの名前を取得（複数）
		String[] selectedTagNamesArr = request.getParameterValues("selectedTags");
		List<String> selectedTagNames = new ArrayList<>();

		if (selectedTagNamesArr != null) {
			for (String name : selectedTagNamesArr) {
				if (name != null && !name.trim().isEmpty()) {
					selectedTagNames.add(name.trim());
				}
			}
		}

		// 2. 新規タグ（カンマ区切り）を取得して分割
		String newTagStr = request.getParameter("newTag");
		List<String> newTagNames = newTagStr != null ? Arrays.stream(newTagStr.split(","))
				.map(String::trim)
				.filter(s -> !s.isEmpty())
				.collect(Collectors.toList()) : List.of();
		
		// 両方のリストをまとめる
		List<String> tagNames = new ArrayList<>();
		tagNames.addAll(selectedTagNames); // 既存タグ
		tagNames.addAll(newTagNames);      // 新規タグ

		MovieRegisterService service = new MovieRegisterService();
		boolean isRegistered = false;

		if ("library".equals(action)) {
			isRegistered = service.addMovieToLibraryWithTags(
					user.getId(), movie.getTitle(), movie.getGenre(),
					movie.getDescription(), movie.getTmdbId(),
					movie.getThumbnailUrl(), tagNames);
		} else if ("watchlist".equals(action)) {
			isRegistered = service.addMovieToWatchlistWithTags(
					user.getId(), movie.getTmdbId(), movie.getTitle(),
					movie.getGenre(), movie.getDescription(),
					movie.getThumbnailUrl(), tagNames);
		}

		// 登録完了後セッションをクリア
		session.removeAttribute("selectedMovie");
		session.removeAttribute("addAction");

		if (isRegistered) {
			response.sendRedirect("/movieLog/detail?tmdbId=" + movie.getTmdbId());
		} else {
			request.setAttribute("error", "登録に失敗しました。");
			request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
		}
	}
}
