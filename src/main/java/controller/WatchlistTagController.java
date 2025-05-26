package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;
import dao.WatchlistTagDAO;
import dto.MovieDTO;
import dto.WatchlistTagDTO;

public class WatchlistTagController extends HttpServlet {

    // ウォッチリストに映画を追加する処理
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int movieId = Integer.parseInt(request.getParameter("movieId"));
            int userId = (int) request.getSession().getAttribute("userId");  // ログインユーザーのIDをセッションから取得

            // 映画情報をデータベースから取得
            MovieDAO movieDAO = new MovieDAO();
            MovieDTO movieDTO = movieDAO.getMovieDetailsById(movieId);

            // ウォッチリストに映画を追加する処理（例: WatchlistDAOに追加）
            // 追加後、タグを関連付ける

            int tagId = Integer.parseInt(request.getParameter("tagId"));  // フォームから送られてくるタグID

            WatchlistTagDTO watchlistTagDTO = new WatchlistTagDTO(movieId, tagId);  // 動画IDとタグIDをDTOにセット
            WatchlistTagDAO watchlistTagDAO = new WatchlistTagDAO();
            watchlistTagDAO.addTagToWatchlist(watchlistTagDTO.getWatchlistId(), watchlistTagDTO.getTagId());  // タグをウォッチリストに追加

            // ウォッチリストページにリダイレクト
            response.sendRedirect("watchlist.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "ウォッチリストにタグを追加できませんでした");
        }
    }
}
