package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import service.UserDeleteService;


@WebServlet("/delete")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//deleteConfirm.jspへフォワード
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/deleteConfirm.jsp");
		rd.forward(request, response);
	}

	// POSTリクエスト時に、退会処理を行い、結果に応じて遷移
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String password = request.getParameter("password");

	    HttpSession session = request.getSession(false);
	    User user = (session != null) ? (User) session.getAttribute("user") : null;

	    if (user == null) {
	        request.setAttribute("deleteError", "セッションが切れています。もう一度ログインしてください。");
	        RequestDispatcher rd = request.getRequestDispatcher("/jsp/deleteConfirm.jsp");
	        rd.forward(request, response);
	        return;
	    }

	    if (password == null || !user.getPassword().equals(password)) {
	        request.setAttribute("deleteError", "パスワードが違います。");
	        RequestDispatcher rd = request.getRequestDispatcher("/jsp/deleteConfirm.jsp");
	        rd.forward(request, response);
	        return;
	    }

	    UserDeleteService deleteService = new UserDeleteService();
	    boolean result = false;
	    try {
	        result = deleteService.userDeleteDo(user);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    if (result) {
	        session.invalidate(); // セッションを破棄
	        // リダイレクトを使って完了画面に遷移
	        response.sendRedirect("/movieLog/jsp/deleteDone.jsp");
	    } else {
	        request.setAttribute("deleteError", "登録情報の削除に失敗しました。");
	        RequestDispatcher rd = request.getRequestDispatcher("/jsp/deleteConfirm.jsp");
	        rd.forward(request, response);
	    }
	}


}
