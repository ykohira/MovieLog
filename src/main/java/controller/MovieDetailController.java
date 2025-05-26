package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LibraryDAO;
import dao.LibraryDAOImpl;
import dao.MovieDAO;
import dao.WatchlistDAO;
import domain.User;
import dto.MovieDTO;

@WebServlet("/detail")
public class MovieDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// TMDb IDの取得
		String idStr = request.getParameter("tmdbId");
		int tmdbId = Integer.parseInt(idStr);

		// 映画の詳細取得
		MovieDAO movieDAO = new MovieDAO();
		MovieDTO movie = movieDAO.getMovieDetailsById(tmdbId);

		// ログインユーザーの取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		boolean alreadyInLibrary = false;
		boolean alreadyInWatchlist = false;

		if (user != null) {
			int userId = user.getId();

			// ライブラリチェック
			LibraryDAO libraryDAO = new LibraryDAOImpl();
			try {
				alreadyInLibrary = libraryDAO.isAlreadyRegistered(userId, tmdbId);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			// ウォッチリストチェック
			WatchlistDAO watchlistDAO = new WatchlistDAO();
			alreadyInWatchlist = watchlistDAO.isAlreadyInWatchlist(userId, tmdbId);
		}

		// 属性をセット
		request.setAttribute("movie", movie);
		request.setAttribute("alreadyInLibrary", alreadyInLibrary);
		request.setAttribute("alreadyInWatchlist", alreadyInWatchlist);

		// 詳細ページにフォワード
		request.getRequestDispatcher("/jsp/movieDetail.jsp").forward(request, response);
	}
}
