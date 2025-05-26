package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.WatchlistDAO;
import domain.User;

@WebServlet("/deleteWatchlist")
public class DeleteWatchlistController extends HttpServlet {
	private WatchlistDAO watchlistDAO = new WatchlistDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		try {

			int tmdbId = Integer.parseInt(request.getParameter("tmdbId"));

			boolean success = watchlistDAO.removeFromWatchlist(user.getId(), tmdbId);

			if (success) {
				// 削除後はライブラリ一覧ページへリダイレクト
				response.sendRedirect(request.getContextPath() + "/watchlist");

			} else {
				// 失敗した場合はエラー画面などへ
				response.sendRedirect("/movieLog/error.jsp");
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect("/movieLog/error.jsp");
		}
	}
}
