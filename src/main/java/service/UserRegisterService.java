package service;

import java.sql.SQLException;

import dao.UserDAOImpl;
import domain.User;
import dto.UserDTO;

public class UserRegisterService {
	public boolean userEntryConfirm(User user) throws SQLException {
		
		//DBにユーザーが既に存在するかチェック
		UserDAOImpl userDAO = new UserDAOImpl();
		UserDTO userDTO = userDAO.getUserByName(user.getUserName());
		
		//ユーザーが存在しない場合→登録内容確認画面に画面遷移させる
		if(userDTO == null) {
			return true;
		} else {
			//既に存在する場合
			return false;
		}
	}
	
	public boolean userEntryDo(User user) throws SQLException {
		UserDAOImpl userDAO = new UserDAOImpl();
		UserDTO dto = new UserDTO(user.getUserName(),user.getPassword(),null);
		int result = userDAO.addUser(dto);
		if(result == 1) {
			return true;
		}else {
			return false;
		}
	}

}
