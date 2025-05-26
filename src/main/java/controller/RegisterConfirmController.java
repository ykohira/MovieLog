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
import service.UserRegisterService;

@WebServlet("/registerConfirm")
public class RegisterConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		//登録する会員情報をDomainに格納
		User user = new User(userName,password,null);
		
		UserRegisterService userRegister = new UserRegisterService();
		boolean result = false;
		try {
			result = userRegister.userEntryDo(user);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//登録に成功した場合はresultにtrueが格納されている
		if(result == true) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/registerDone.jsp");
			rd.forward(request, response);
		}else {
			//新規会員登録に失敗した場合はエラーメッセージを用意して会員登録画面へ戻す
			request.setAttribute("registerError", "新規会員登録に失敗しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/register.jsp");
			rd.forward(request, response);
		}
	}

}
