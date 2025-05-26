package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LibraryDAO;
import dao.LibraryDAOImpl;

@WebServlet("/deleteMovie")
public class DeleteMovieController extends HttpServlet {
    private LibraryDAO libraryDao = new LibraryDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int movieId = Integer.parseInt(request.getParameter("movie_id"));

            boolean success = libraryDao.deleteMovieById(movieId);

            if (success) {
                // 削除後はライブラリ一覧ページへリダイレクト
            	response.sendRedirect(request.getContextPath() + "/library");

            } else {
                // 失敗した場合はエラー画面などへ
                response.sendRedirect("/movieLog/error.jsp");
            }

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("/movieLog/error.jsp");
        }
    }
}
