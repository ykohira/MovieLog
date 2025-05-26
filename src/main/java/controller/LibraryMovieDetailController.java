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

import dao.LibraryDAO;
import dao.LibraryDAOImpl;
import dao.TagDAO;
import dto.LibraryDTO;
import dto.TagDTO;

@WebServlet("/libraryDetail")
public class LibraryMovieDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");

        // movie IDの取得
        String idStr = request.getParameter("movie_id");
        int movieId = Integer.parseInt(idStr);

        // 映画の詳細取得
        LibraryDAO libraryDAO = new LibraryDAOImpl();
        Optional<LibraryDTO> movie = java.util.Optional.empty();
		try {
			movie = libraryDAO.getMovieById(movieId);
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
				tagList = tagDAO.getTagsByLibraryId(movieId);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}  // ※StringでもTagDTOでもOK

			request.setAttribute("tagList", tagList);

		    request.setAttribute("movie", movie.get());
		    request.getRequestDispatcher("/jsp/libraryDetail.jsp").forward(request, response);
		} else {
		    response.sendRedirect("/movieLog/error.jsp"); // データが見つからなかった場合
		}

    }
}
