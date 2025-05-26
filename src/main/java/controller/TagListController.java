package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TagDAO;
import domain.User;
import dto.TagDTO;

/**
 * Servlet implementation class TagListController
 */
@WebServlet("/tagList")
public class TagListController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if (user == null) {
                // ログインしていない場合はログイン画面にリダイレクト
                response.sendRedirect("login.jsp");
                return;
            }
        	
            TagDAO tagDAO = new TagDAO();
            List<TagDTO> tagList = tagDAO.getAllTags(user.getId());
           
            request.setAttribute("tagList", tagList);
            request.getRequestDispatcher("/jsp/tags.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // エラーページにリダイレクト、またはエラーメッセージ表示
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "タグ一覧の取得に失敗しました。");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // POSTで来てもGETと同じ処理をする
        doGet(request, response);
    }
}
