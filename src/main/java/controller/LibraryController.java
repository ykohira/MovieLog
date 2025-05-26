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

import dao.LibraryDAO;
import dao.LibraryDAOImpl;
import domain.User;
import dto.LibraryDTO;

@WebServlet("/library")
public class LibraryController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // セッションからユーザーIDを取得
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            // ログインしていない場合はログイン画面にリダイレクト
            response.sendRedirect("login.jsp");
            return;
        }
        
        int userId = user.getId();

        try {
            // DAOを使ってそのユーザーの映画一覧を取得
            LibraryDAO dao = new LibraryDAOImpl();
            List<LibraryDTO> movieList = dao.getMoviesByUserId(userId);

            // リクエストにセットしてJSPにフォワード
            request.setAttribute("movieList", movieList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/library.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            // エラーページなどに飛ばすのもあり（今回は簡略化）
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "データベースエラーが発生しました");
        }
    }
}
