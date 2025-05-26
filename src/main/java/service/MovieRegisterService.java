package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.LibraryDAOImpl;
import dao.LibraryTagDAO;
import dao.TagDAO;
import dao.TransactionManager;
import dao.WatchlistDAO;
import dao.WatchlistTagDAO;
import dto.LibraryDTO;
import dto.WatchlistDTO;

public class MovieRegisterService {

	public boolean addMovieToLibraryWithTags(
	        int userId,
	        String title,
	        String genre,
	        String description,
	        int tmdbId,
	        String thumbnailUrl,
	        List<String> tagNames
	) {
	    Connection conn = null;
	    TransactionManager tm = null;

	    try {
	        conn = new dao.BaseDAO().getConnection();
	        tm = new TransactionManager(conn);

	        LibraryDAOImpl libraryDAO = new LibraryDAOImpl();
	        TagDAO tagDAO = new TagDAO(conn);
	        LibraryTagDAO libraryTagDAO = new LibraryTagDAO();

	        // DTO作成
	        LibraryDTO libraryDTO = new LibraryDTO(userId, title, genre, description, tmdbId, thumbnailUrl);

	        // 映画登録 → ID取得
	        int libraryId = libraryDAO.addMovieToLibraryAndReturnId(libraryDTO);

	        if (libraryId == -1) {
	            tm.rollback();
	            return false;
	        }

	        // タグ登録（存在しなければ作成）
	        if (tagNames != null && !tagNames.isEmpty()) {
	            for (String tagName : tagNames) {
	                int tagId = tagDAO.createOrFindTag(tagName, userId);
	                libraryTagDAO.addTagToLibrary(libraryId, tagId);
	            }
	        }

	        tm.commit();
	        return true;

	    } catch (Exception e) {
	        e.printStackTrace();
	        try {
	            if (tm != null) tm.rollback();
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	        return false;
	    } finally {
	        if (tm != null) tm.close();
	    }
	}


//ウォッチリストにタグ込みで登録
	public boolean addMovieToWatchlistWithTags(
	        int userId,
	        int tmdbId,
	        String title,
	        String genre,
	        String description,
	        String thumbnailUrl,
	        List<String> tagNames
	) {
	    Connection conn = null;
	    TransactionManager tm = null;

	    try {
	        conn = new dao.BaseDAO().getConnection();
	        tm = new TransactionManager(conn);

	        WatchlistDAO watchlistDAO = new WatchlistDAO();
	        TagDAO tagDAO = new TagDAO(conn);
	        WatchlistTagDAO watchlistTagDAO = new WatchlistTagDAO();

	        // DTO作成＆登録（戻り値を watchlist_id で取得）
	        WatchlistDTO watchlistDTO = new WatchlistDTO(userId, tmdbId, title, genre, description, thumbnailUrl);
	        int watchlistId = watchlistDAO.addToWatchlistAndReturnId(watchlistDTO);

	        if (watchlistId == -1) {
	            tm.rollback();
	            return false;
	        }

	        // タグの登録
	        if (tagNames != null && !tagNames.isEmpty()) {
	            for (String tagName : tagNames) {
	                int tagId = tagDAO.createOrFindTag(tagName, userId);
	                watchlistTagDAO.addTagToWatchlist(watchlistId, tagId);
	            }
	        }

	        tm.commit();
	        return true;

	    } catch (Exception e) {
	        e.printStackTrace();
	        try {
	            if (tm != null) tm.rollback();
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	        return false;
	    } finally {
	        if (tm != null) tm.close();
	    }
	}

}
