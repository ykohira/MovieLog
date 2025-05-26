package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import dto.LibraryDTO;

public interface LibraryDAO {
    // 映画をライブラリに追加するメソッド（成功・失敗をbooleanで返す）
    boolean addMovieToLibrary(LibraryDTO library) throws SQLException;
    
    // 映画IDでライブラリから映画情報を取得するメソッド（Optionalで返す）
    Optional<LibraryDTO> getMovieById(int movieId) throws SQLException;
    
    List<LibraryDTO> getMoviesByUserId(int userId) throws SQLException;
    
    boolean isAlreadyRegistered(int userId, int tmdbId) throws SQLException;

	boolean deleteMovieById(int movieId) throws SQLException;

}
