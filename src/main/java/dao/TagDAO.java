package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.TagDTO;

public class TagDAO extends BaseDAO {
	private Connection connection;

	public TagDAO(Connection connection) {
		this.connection = connection;
	}

	// コンストラクタで BaseDAO の getConnection() を使う
	public TagDAO() throws SQLException {
		this.connection = new BaseDAO().getConnection();
	}

	// タグ名の重複チェック（ユーザーごと）
	public boolean isTagNameExist(String tagName, int userId) throws SQLException {
		String query = "SELECT COUNT(*) FROM tags WHERE name = ? AND user_id = ?";
		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, tagName);
			stmt.setInt(2, userId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		}
		return false;
	}

	// タグを追加（ユーザーIDを含む）
	public int addTag(TagDTO tag) throws SQLException {
		String sql = "INSERT INTO tags (name, user_id) VALUES (?, ?)";
		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, tag.getName());
			stmt.setInt(2, tag.getUserId()); // user_id をセット
			stmt.executeUpdate();
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		}
		return -1;
	}

	// ID でタグを取得
	public TagDTO getTagById(int id) throws SQLException {
		String query = "SELECT * FROM tags WHERE id = ?";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new TagDTO(rs.getInt("id"), rs.getString("name"), rs.getInt("user_id"));
				}
			}
		} catch (SQLException e) {
			System.out.println("タグの取得に失敗しました: " + e.getMessage());
			throw e;
		}
		return null; // 見つからなかった場合
	}

	// ユーザーごとにすべてのタグを取得
	public List<TagDTO> getAllTags(int userId) throws SQLException {
		List<TagDTO> tags = new ArrayList<>();
		String query = "SELECT * FROM tags WHERE user_id = ?";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, userId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					tags.add(new TagDTO(rs.getInt("id"), rs.getString("name"), rs.getInt("user_id")));
				}
			}
		} catch (SQLException e) {
			System.out.println("タグの取得に失敗しました: " + e.getMessage());
			throw e;
		}

		return tags;
	}

	// タグを削除
	public void deleteTag(int id) throws SQLException {
		String query = "DELETE FROM tags WHERE id = ?";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("タグの削除に失敗しました: " + e.getMessage());
			throw e;
		}
	}

	// タグが存在すればIDを返し、存在しなければ新規作成してIDを返す
	public int createOrFindTag(String tagName, int userId) throws SQLException {
		// 1. 既存のタグを検索
		String findQuery = "SELECT id FROM tags WHERE name = ? AND user_id = ?";
		try (PreparedStatement findStmt = connection.prepareStatement(findQuery)) {
			findStmt.setString(1, tagName);
			findStmt.setInt(2, userId);
			try (ResultSet rs = findStmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("id"); // 既に存在するタグのIDを返す
				}
			}
		}

		// 2. 存在しなければ新規作成
		String insertQuery = "INSERT INTO tags (name, user_id) VALUES (?, ?)";
		try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
			insertStmt.setString(1, tagName);
			insertStmt.setInt(2, userId);
			insertStmt.executeUpdate();
			try (ResultSet rs = insertStmt.getGeneratedKeys()) {
				if (rs.next()) {
					return rs.getInt(1); // 新規作成したタグのIDを返す
				}
			}
		}

		throw new SQLException("タグの作成に失敗しました");
	}

	// タグ名とユーザーIDでタグを取得
	public TagDTO getTagByName(String tagName, int userId) throws SQLException {
		String query = "SELECT * FROM tags WHERE name = ? AND user_id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, tagName);
			stmt.setInt(2, userId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new TagDTO(rs.getInt("id"), rs.getString("name"), rs.getInt("user_id"));
				}
			}
		}
		return null;
	}

	// ライブラリIDからタグ一覧を取得
	public List<TagDTO> getTagsByLibraryId(int libraryId) throws SQLException {
		List<TagDTO> tags = new ArrayList<>();
		String query = """
				    SELECT t.id, t.name, t.user_id
				    FROM tags t
				    JOIN library_tag lt ON t.id = lt.tag_id
				    WHERE lt.library_id = ?
				""";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, libraryId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					TagDTO tag = new TagDTO(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getInt("user_id"));
					tags.add(tag);
				}
			}
		} catch (SQLException e) {
			System.out.println("ライブラリIDによるタグ取得に失敗しました: " + e.getMessage());
			throw e;
		}

		return tags;
	}
	
	// ウォッチリストIDからタグ一覧を取得
		public List<TagDTO> getTagsByWatchlistId(int watchlistId) throws SQLException {
			List<TagDTO> tags = new ArrayList<>();
			String query = """
					    SELECT t.id, t.name, t.user_id
					    FROM tags t
					    JOIN watchlist_tag lt ON t.id = lt.tag_id
					    WHERE lt.watchlist_id = ?
					""";

			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setInt(1, watchlistId);
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						TagDTO tag = new TagDTO(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getInt("user_id"));
						tags.add(tag);
					}
				}
			} catch (SQLException e) {
				System.out.println("ライブラリIDによるタグ取得に失敗しました: " + e.getMessage());
				throw e;
			}

			return tags;
		}

}
