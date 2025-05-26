package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WatchlistTagDAO extends BaseDAO {

    public WatchlistTagDAO() throws SQLException {
        super();
    }

    // 映画IDとタグIDを関連付けて登録
    public void addTagToWatchlist(int watchlistId, int tagId) throws SQLException {
        String query = "INSERT INTO watchlist_tag (watchlist_id, tag_id) VALUES (?, ?)";

        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, watchlistId);
            stmt.setInt(2, tagId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ウォッチリストにタグを追加できませんでした: " + e.getMessage());
            throw e;
        }
    }

    // 映画IDとタグIDの組み合わせを削除
    public void removeTagFromWatchlist(int watchlistId, int tagId) throws SQLException {
        String query = "DELETE FROM watchlist_tag WHERE watchlist_id = ? AND tag_id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, watchlistId);
            stmt.setInt(2, tagId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ウォッチリストからタグを削除できませんでした: " + e.getMessage());
            throw e;
        }
    }

    // 指定したウォッチリストの映画に関連するタグを取得
    public void getTagsForWatchlist(int watchlistId) throws SQLException {
        String query = "SELECT t.id, t.name FROM tags t " +
                       "JOIN watchlist_tag wt ON t.id = wt.tag_id " +
                       "WHERE wt.watchlist_id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, watchlistId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Tag: " + rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            System.out.println("ウォッチリストのタグ取得に失敗しました: " + e.getMessage());
            throw e;
        }
    }
}
