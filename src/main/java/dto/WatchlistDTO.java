package dto;

import java.sql.Timestamp;

public class WatchlistDTO {
	private int id;
	private int userId;
	private int tmdbId;
	private String title;
	private String genre;
	private String description;
	private String thumbnailUrl;
	private Timestamp createdAt;

	// コンストラクタ
	public WatchlistDTO(int id, int userId, int tmdbId, String title, String genre, String description,
			String thumbnailUrl, Timestamp createdAt) {
		this.id = id;
		this.userId = userId;
		this.tmdbId = tmdbId;
		this.title = title;
		this.genre = genre;
		this.description = description;
		this.thumbnailUrl = thumbnailUrl;
		this.createdAt = createdAt;
	}

	// INSERT用の簡易コンストラクタ（movieId, createdAt省略）
	public WatchlistDTO(int userId, int tmdbId, String title, String genre, String description,
			String thumbnailUrl) {
		this.userId = userId;
		this.tmdbId = tmdbId;
		this.title = title;
		this.genre = genre;
		this.description = description;
		this.thumbnailUrl = thumbnailUrl;
	}

	// ゲッター・セッター
	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public int getTmdbId() {
		return tmdbId;
	}

	public String getTitle() {
		return title;
	}

	public String getGenre() {
		return genre;
	}

	public String getDescription() {
		return description;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setTmdbId(int tmdbId) {
		this.tmdbId = tmdbId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
