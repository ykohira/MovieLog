package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;
import dto.MovieDTO;

@WebServlet("/search")
public class SearchResultController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");

        // パラメータの取得
        String keyword = request.getParameter("query");

        // DAOを使って映画情報を取得
        MovieDAO dao = new MovieDAO();
        List<MovieDTO> movieList = dao.searchMovies(keyword); // ← searchMovieListに変更しておいてね！

        // 結果をリクエストスコープに保存
        request.setAttribute("movieList", movieList);
        request.setAttribute("keyword", keyword);

        // JSPにフォワード
        request.getRequestDispatcher("/jsp/searchResult.jsp").forward(request, response);
    }
}
