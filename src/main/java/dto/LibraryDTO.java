package dto;

import java.sql.Timestamp;

public class LibraryDTO {
    private int movieId;
    private int userId;
    private String title;
    private String genre;
    private String description;
    private int tmdbId;
    private Timestamp createdAt;
    private String thumbnailUrl;  // ★ 追加

    // コンストラクタ（データ取得用）
    public LibraryDTO(int movieId, int userId, String title, String genre, String description, int tmdbId, Timestamp createdAt, String thumbnailUrl) {
        this.movieId = movieId;
        this.userId = userId;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.tmdbId = tmdbId;
        this.createdAt = createdAt;
        this.thumbnailUrl = thumbnailUrl; // ★ 追加
    }

    // INSERT用の簡易コンストラクタ（movieId, createdAt省略）
    public LibraryDTO(int userId, String title, String genre, String description, int tmdbId, String thumbnailUrl) {
        this.userId = userId;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.tmdbId = tmdbId;
        this.thumbnailUrl = thumbnailUrl; // ★ 追加
    }

    // GetterとSetter
    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getTmdbId() { return tmdbId; }
    public void setTmdbId(int tmdbId) { this.tmdbId = tmdbId; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public String getThumbnailUrl() { return thumbnailUrl; }  // ★ 追加
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }  // ★ 追加
}
