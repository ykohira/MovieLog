package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.PropertyLoader;

public class BaseDAO {
    
    private static final String DB_URL = PropertyLoader.get("DB_URL");
    private static final String DB_USER = PropertyLoader.get("DB_USER");
    private static final String DB_PASSWORD = PropertyLoader.get("DB_PASSWORD");

    private Connection conn;

    // DB接続を取得するメソッド
    public Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            try {
                // PostgreSQL JDBC ドライバのロード（JDBC 4.0以降不要だが書いてもOK）
                Class.forName("org.postgresql.Driver");

                // 接続の取得
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("JDBC ドライバが見つかりません", e);
            } catch (SQLException e) {
                throw e;
            }
        }
        return conn;
    }
}
