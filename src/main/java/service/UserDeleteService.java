package service;

import java.sql.SQLException;

import dao.UserDAOImpl;
import domain.User;
import dto.UserDTO;

public class UserDeleteService {

	public boolean userDeleteDo(User user) throws SQLException {

		//削除したいユーザーのuserNameをDTOに格納
		UserDAOImpl userDAO = new UserDAOImpl();
		UserDTO dto = new UserDTO(user.getUserName(), null, null);
		int result = userDAO.delete(dto);

		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	public User userDeleteCheck(String userName, String password) throws SQLException {
			
			UserDAOImpl userDAO = new UserDAOImpl();
			
			
			//Controllerから受け取ったパラメーターをDAOのメソッドへ渡す
			UserDTO userDTO = userDAO.getUserByName(userName);
			
			//DAOでのselect成功時はUserDTOにログインしたユーザー情報が格納されている
			
			//ユーザーが入力した値とDBの値が等しいかチェック
			if(userDTO != null && userDTO.getPassword().equals(password)) {
				//等しい場合はDTOの情報をDomainに移行
				User user = new User(userDTO.getUserName(), userDTO.getPassword(),null);
				user.setUserName(userDTO.getUserName());
				return user;
			}
			
			return null;
		}

}
