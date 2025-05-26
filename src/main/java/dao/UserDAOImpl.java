package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UserDTO;

public class UserDAOImpl extends BaseDAO implements UserDAO {

    @Override
    public int addUser(UserDTO user) throws SQLException {
    	int result = 0;
        Connection conn = getConnection();
        TransactionManager tm = new TransactionManager(conn);
        
        try {
        	PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (user_name, password, email) VALUES (?, ?, ?)");
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            result = stmt.executeUpdate();
            tm.commit();
        } catch(SQLException e) {
        	tm.rollback();
        	e.printStackTrace();
        }
        tm.close();
		return result;
    }

    //ユーザー名でデータベースを検索
    @Override
    public UserDTO getUserByName(String userName) throws SQLException {
        Connection conn = getConnection();
        String sql = "SELECT * FROM users WHERE user_name = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new UserDTO(
                		rs.getInt("id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getTimestamp("created_at")
                );
            }
        }
        return null; // ユーザーが見つからなかった場合はnullを返す
    }
    
    
    public int edit(UserDTO dto) throws SQLException {
        int result = 0;
        Connection conn = getConnection();
        TransactionManager tm = new TransactionManager(conn);

        try (PreparedStatement ps = conn.prepareStatement("UPDATE users SET user_name = ?, password = ? WHERE id = ?")) {

            ps.setString(1, dto.getUserName());
            ps.setString(2, dto.getPassword());
            ps.setLong(3, dto.getId());

            result = ps.executeUpdate();
            tm.commit();

        } catch (SQLException e) {
            tm.rollback();
            e.printStackTrace(); // ログ出力などに切り替え可能
        } finally {
            tm.close(); // TransactionManagerがconnを閉じてくれる想定
        }

        return result;
    }

	public int delete(UserDTO dto) throws SQLException {
		int result = 0;

		//データベースへ接続
		Connection conn = getConnection();
		//トランザクション処理を開始
		TransactionManager tm = new TransactionManager(conn);

		//データベースへ接続
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE user_name = ?");
			ps.setString(1, dto.getUserName());

			//DBへのdeleteが成功した件数がint型で返却される
			result = ps.executeUpdate();
			tm.commit();
		} catch (SQLException e) {
			tm.rollback();
			e.printStackTrace();
		}
		tm.close();
		return result;
	}

    
}
