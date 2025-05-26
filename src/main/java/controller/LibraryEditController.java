package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LibraryDAO;
import dao.LibraryDAOImpl;
import dto.LibraryDTO;


@WebServlet("/libraryEdit")
public class LibraryEditController extends HttpServlet {
    private LibraryDAO libraryDao = new LibraryDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int movieId = Integer.parseInt(request.getParameter("movie_id"));
            java.util.Optional<LibraryDTO> movieOpt = libraryDao.getMovieById(movieId);

            if (movieOpt.isPresent()) {
                request.setAttribute("movie", movieOpt.get());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/movieEdit.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("/MovieLog/error.jsp"); // データがなかった場合のエラーページなど
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/MovieLog/error.jsp"); // エラー時の遷移
        }
    }
}

