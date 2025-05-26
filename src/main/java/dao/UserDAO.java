package dao;

import java.sql.SQLException;

import dto.UserDTO;

public interface UserDAO {
    int addUser(UserDTO user) throws SQLException;
    UserDTO getUserByName(String userName) throws SQLException;
}
