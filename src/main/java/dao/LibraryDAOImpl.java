package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dto.LibraryDTO;

public class LibraryDAOImpl extends BaseDAO implements LibraryDAO {

    @Override
    public boolean addMovieToLibrary(LibraryDTO library) throws SQLException {
        String sql = "INSERT INTO library (user_id, title, genre, description, tmdb_id, thumbnail_url) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, library.getUserId());
            stmt.setString(2, library.getTitle());
            stmt.setString(3, library.getGenre());
            stmt.setString(4, library.getDescription());
            stmt.setInt(5, library.getTmdbId());
            stmt.setString(6, library.getThumbnailUrl());

            return stmt.executeUpdate() > 0; // 成功したかどうか
        }
    }

    @Override
    public Optional<LibraryDTO> getMovieById(int movieId) throws SQLException {
        String sql = "SELECT * FROM library WHERE movie_id = ?";
        
        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, movieId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    LibraryDTO movie = new LibraryDTO(
                            rs.getInt("movie_id"),
                            rs.getInt("user_id"),
                            rs.getString("title"),
                            rs.getString("genre"),
                            rs.getString("description"),
                            rs.getInt("tmdb_id"),
                            rs.getTimestamp("created_at"),
                            rs.getString("thumbnail_url")
                    );
                    return Optional.of(movie);
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public List<LibraryDTO> getMoviesByUserId(int userId) throws SQLException {
        List<LibraryDTO> movieList = new ArrayList<>();
        String sql = "SELECT * FROM library WHERE user_id = ? ORDER BY created_at DESC";

        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    LibraryDTO movie = new LibraryDTO(
                            rs.getInt("movie_id"),
                            rs.getInt("user_id"),
                            rs.getString("title"),
                            rs.getString("genre"),
                            rs.getString("description"),
                            rs.getInt("tmdb_id"),
                            rs.getTimestamp("created_at"),
                            rs.getString("thumbnail_url")
                    );
                    movieList.add(movie);
                }
            }
        }

        return movieList;
    }

    @Override
    public boolean isAlreadyRegistered(int userId, int tmdbId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM library WHERE user_id = ? AND tmdb_id = ?";

        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);
            stmt.setInt(2, tmdbId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }

        return false;
    }

    @Override
    public boolean deleteMovieById(int movieId) throws SQLException {
        String sql = "DELETE FROM library WHERE movie_id = ?";
        
        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, movieId);
            return stmt.executeUpdate() > 0;
        }
    }

    public int addMovieToLibraryAndReturnId(LibraryDTO library) throws SQLException {
        String sql = "INSERT INTO library (user_id, title, genre, description, tmdb_id, thumbnail_url) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, library.getUserId());
            stmt.setString(2, library.getTitle());
            stmt.setString(3, library.getGenre());
            stmt.setString(4, library.getDescription());
            stmt.setInt(5, library.getTmdbId());
            stmt.setString(6, library.getThumbnailUrl());

            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // 追加されたlibrary_id
                }
            }
        }
        throw new SQLException("ライブラリ登録に失敗しました");
    }
    
    
}
