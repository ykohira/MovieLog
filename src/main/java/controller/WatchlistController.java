package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.WatchlistDAO;
import domain.User;
import dto.WatchlistDTO;

@WebServlet("/watchlist")
public class WatchlistController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // セッションからユーザーIDを取得
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            // ログインしていない場合はログイン画面にリダイレクト
            response.sendRedirect("/movieLog/jsp/login.jsp");
            return;
        }
        
        int userId = user.getId();

        // DAOを使ってそのユーザーのウォッチリスト一覧を取得
		WatchlistDAO dao = new WatchlistDAO();
		List<WatchlistDTO> watchlist = dao.getWatchlist(userId);

		// リクエストにセットしてJSPにフォワード
		request.setAttribute("watchlist", watchlist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/watchlist.jsp");
		dispatcher.forward(request, response);
    }
}
