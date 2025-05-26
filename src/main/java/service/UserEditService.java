package service;

import java.sql.SQLException;

import dao.UserDAOImpl;
import dto.UserDTO;

public class UserEditService {
	
	public boolean userEditDo(int id, String userName, String password) throws SQLException {
		UserDTO dto = new UserDTO(id,userName,password,null,null);
		UserDAOImpl userDAO = new UserDAOImpl();
		int result = userDAO.edit(dto);
		
		if(result == 1) {
			return true;
		}else {
			return false;
		}
	}

}
