package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dto.WatchlistDTO;

public class WatchlistDAO extends BaseDAO {

    public boolean addToWatchlist(WatchlistDTO watchlist) {
        String sql = "INSERT INTO watchlist (user_id, tmdb_id, title, genre, description, thumbnail_url) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, watchlist.getUserId());
            stmt.setInt(2, watchlist.getTmdbId());
            stmt.setString(3, watchlist.getTitle());
            stmt.setString(4, watchlist.getGenre());
            stmt.setString(5, watchlist.getDescription());
            stmt.setString(6, watchlist.getThumbnailUrl());

            return stmt.executeUpdate() > 0; // 追加に成功した場合 true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeFromWatchlist(int userId, int tmdbId) {
        String sql = "DELETE FROM watchlist WHERE user_id = ? AND tmdb_id = ?";
        
        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);
            stmt.setInt(2, tmdbId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isAlreadyInWatchlist(int userId, int tmdbId) {
        String sql = "SELECT COUNT(*) FROM watchlist WHERE user_id = ? AND tmdb_id = ?";
        
        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);
            stmt.setInt(2, tmdbId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<WatchlistDTO> getWatchlist(int userId) {
        List<WatchlistDTO> watchlist = new ArrayList<>();
        String sql = "SELECT id, user_id, tmdb_id, title, genre, description, thumbnail_url, created_at FROM watchlist WHERE user_id = ?";
        
        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    WatchlistDTO dto = new WatchlistDTO(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getInt("tmdb_id"),
                            rs.getString("title"),
                            rs.getString("genre"),
                            rs.getString("description"),
                            rs.getString("thumbnail_url"),
                            rs.getTimestamp("created_at")
                    );
                    watchlist.add(dto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return watchlist;
    }

    public Optional<WatchlistDTO> getMovieByWatchlistId(int id) throws SQLException {
        String sql = "SELECT * FROM watchlist WHERE id = ?";
        
        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    WatchlistDTO movie = new WatchlistDTO(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getInt("tmdb_id"),
                            rs.getString("title"),
                            rs.getString("genre"),
                            rs.getString("description"),
                            rs.getString("thumbnail_url"),
                            rs.getTimestamp("created_at")
                    );
                    return Optional.of(movie);
                }
            }
        }

        return Optional.empty();
    }
    
    
 // 映画をウォッチリストに追加して、追加されたIDを返す
    public int addToWatchlistAndReturnId(WatchlistDTO watchlistDTO) throws SQLException {
        String query = "INSERT INTO watchlist (user_id, tmdb_id, title, genre, description, thumbnail_url) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, watchlistDTO.getUserId());
            stmt.setInt(2, watchlistDTO.getTmdbId());
            stmt.setString(3, watchlistDTO.getTitle());
            stmt.setString(4, watchlistDTO.getGenre());
            stmt.setString(5, watchlistDTO.getDescription());
            stmt.setString(6, watchlistDTO.getThumbnailUrl());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("ウォッチリストに映画を追加できませんでした。");
            }

            // 自動生成されたIDを取得
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // 生成されたIDを返す
                } else {
                    throw new SQLException("ウォッチリストのIDが取得できませんでした。");
                }
            }
        }
    }
    
}
