package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LibraryDAOImpl;
import dao.WatchlistDAO;
import domain.User;
import dto.LibraryDTO;
import dto.WatchlistDTO;
import service.UserLoginService;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            // ログインしていない場合はログイン画面にリダイレクト
            response.sendRedirect("login.jsp");
            return;
        }
        
		LibraryDAOImpl libraryDAOImpl = new LibraryDAOImpl();
		List<LibraryDTO> uMovieList = null;
		WatchlistDAO watchlistDAO = new WatchlistDAO();
		List<WatchlistDTO> uWatchlist = null;
		try {
			uMovieList = libraryDAOImpl.getMoviesByUserId(user.getId());
			uWatchlist = watchlistDAO.getWatchlist(user.getId());
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		request.setAttribute("uMovieList", uMovieList);
		request.setAttribute("uWatchlist", uWatchlist);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/home.jsp");
		rd.forward(request, response);
	} 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        // ユーザーIDを取得
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        UserLoginService loginService = new UserLoginService();
		User user = null;
		try {
			user = loginService.loginCheck(userName, password);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		if(user != null) {
			//ログイン成功した場合はセッションスコープにユーザ情報が入ったDomainを保存
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			LibraryDAOImpl libraryDAOImpl = new LibraryDAOImpl();
			List<LibraryDTO> uMovieList = null;
			WatchlistDAO watchlistDAO = new WatchlistDAO();
			List<WatchlistDTO> uWatchlist = null;
			try {
				uMovieList = libraryDAOImpl.getMoviesByUserId(user.getId());
				uWatchlist = watchlistDAO.getWatchlist(user.getId());
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			request.setAttribute("uMovieList", uMovieList);
			request.setAttribute("uWatchlist", uWatchlist);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/home.jsp");
			rd.forward(request, response);
		} else {
			//ログインIDやパスワードが間違っていた場合はリクエストスコープにエラーメッセージを格納
			request.setAttribute("loginError", "ログインIDまたはパスワードが間違っています。");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
			rd.forward(request, response);
		}
    }
    
}