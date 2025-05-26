package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TagDAO;
import dao.WatchlistDAO;
import dto.TagDTO;
import dto.WatchlistDTO;

@WebServlet("/watchlistDetail")
public class WatchlistDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// movie IDの取得
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);

		// 映画の詳細取得
		WatchlistDAO watchlistDAO = new WatchlistDAO();
		Optional<WatchlistDTO> movie = java.util.Optional.empty();
		try {
			movie = watchlistDAO.getMovieByWatchlistId(id);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		if (movie.isPresent()) {
			TagDAO tagDAO = null;
			try {
				tagDAO = new TagDAO();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			// 映画IDをもとにタグ取得
			List<TagDTO> tagList = null;
			try {
				tagList = tagDAO.getTagsByWatchlistId(id);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} // ※StringでもTagDTOでもOK

			request.setAttribute("tagList", tagList);
			request.setAttribute("movie", movie.get());
			request.getRequestDispatcher("/jsp/watchlistDetail.jsp").forward(request, response);
		} else {
			response.sendRedirect("/movieLog/error.jsp"); // データが見つからなかった場合
		}

	}
}
