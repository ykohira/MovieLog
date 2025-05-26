package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.BaseDAO;
import dao.TagDAO;
import dao.TransactionManager;

public class TagDeleteService {

    public void deleteTag(int tagId) throws SQLException {
        Connection conn = null;
        TransactionManager tm = null;

        try {
            conn = new BaseDAO().getConnection();
            tm = new TransactionManager(conn);

            TagDAO tagDAO = new TagDAO(conn); // Connectionを受け取るコンストラクタを使う
            tagDAO.deleteTag(tagId);

            tm.commit();
        } catch (SQLException e) {
            if (tm != null) {
                try {
                    tm.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (tm != null) {
                tm.close();
            }
        }
    }
}
