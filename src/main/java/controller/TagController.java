package controller;

import java.io.IOException;
import java.sql.SQLException;
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
import service.TagService;

@WebServlet("/createTag")
public class TagController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            // ログインしていない場合はログイン画面にリダイレクト
            response.sendRedirect("login.jsp");
            return;
        }

		try {
			// 登録後、ユーザーのタグを取得して一覧ページに渡す
			TagDAO tagDAO = new TagDAO();
			List<TagDTO> tagList = tagDAO.getAllTags(user.getId()); // ユーザーIDを渡して、特定のユーザーのタグのみ取得

			request.setAttribute("tagList", tagList);
			request.getRequestDispatcher("/jsp/tags.jsp").forward(request, response);

		} catch (SQLException e) {
			request.setAttribute("errorMessage", "システムエラーが発生しました。");
			e.printStackTrace();
			request.getRequestDispatcher("/jsp/createTag.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // タグ名をUTF-8エンコーディングで受け取る
        String tagName = request.getParameter("tagName");


        try {
        	HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if (user == null) {
                // ログインしていない場合はログイン画面にリダイレクト
                response.sendRedirect("login.jsp");
                return;
            }
            
            TagService tagService = new TagService();
            tagService.registerTag(tagName, user.getId());
          

            // 登録後、ユーザーのタグを取得して一覧ページに渡す
            TagDAO tagDAO = new TagDAO();
            List<TagDTO> tagList = tagDAO.getAllTags(user.getId());  // ユーザーIDを渡して、特定のユーザーのタグのみ取得

            request.setAttribute("tagList", tagList);
            request.setAttribute("successMessage", "タグを登録しました（タグ名: " + tagName + "）");

            request.getRequestDispatcher("/tagList").forward(request, response);

        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/jsp/createTag.jsp").forward(request, response);

        } catch (SQLException e) {
            request.setAttribute("errorMessage", "システムエラーが発生しました。");
            e.printStackTrace();
            request.getRequestDispatcher("/jsp/createTag.jsp").forward(request, response);
        }
    }
}
