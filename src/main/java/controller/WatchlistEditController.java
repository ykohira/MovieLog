package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WatchlistDAO;
import dto.WatchlistDTO;


@WebServlet("/watchlistEdit")
public class WatchlistEditController extends HttpServlet {
    private WatchlistDAO watchlistDAO = new WatchlistDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            java.util.Optional<WatchlistDTO> movieOpt = watchlistDAO.getMovieByWatchlistId(id);

            if (movieOpt.isPresent()) {
                request.setAttribute("movie", movieOpt.get());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/watchlistEdit.jsp");
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

