package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TagDTO;

public class LibraryTagDAO extends BaseDAO {

    public LibraryTagDAO() throws SQLException {
        super();
    }

    // 映画IDとタグIDを関連付けて登録
    public void addTagToLibrary(int libraryId, int tagId) throws SQLException {
        String query = "INSERT INTO library_tag (library_id, tag_id) VALUES (?, ?)";

        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, libraryId);
            stmt.setInt(2, tagId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ライブラリにタグを追加できませんでした: " + e.getMessage());
            throw e;
        }
    }

    // 映画IDとタグIDの組み合わせを削除
    public void removeTagFromLibrary(int libraryId, int tagId) throws SQLException {
        String query = "DELETE FROM library_tag WHERE library_id = ? AND tag_id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, libraryId);
            stmt.setInt(2, tagId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ライブラリからタグを削除できませんでした: " + e.getMessage());
            throw e;
        }
    }

    // 指定したライブラリの映画に関連するタグを取得
    public List<TagDTO> getTagsForLibrary(int libraryId) throws SQLException {
        List<TagDTO> tags = new ArrayList<>();
        String query = "SELECT t.id, t.name FROM tags t " +
                       "JOIN library_tag lt ON t.id = lt.tag_id " +
                       "WHERE lt.library_id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, libraryId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    tags.add(new TagDTO(rs.getInt("id"), rs.getString("name"), libraryId));
                }
            }
        } catch (SQLException e) {
            System.out.println("ライブラリのタグ取得に失敗しました: " + e.getMessage());
            throw e;
        }

        return tags;
    }

}
