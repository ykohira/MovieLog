package domain;

import java.sql.Timestamp;

public class Library {
    private int movieId;
    private int userId;
    private String title;
    private String genre;
    private String description;
    private int tmdbId;
    private Timestamp createdAt;

    public Library(int userId, String title, String genre, String description, int tmdbId, Timestamp createdAt) {
        this.userId = userId;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.tmdbId = tmdbId;
        this.createdAt = createdAt;
    }

    // ユーザーが映画をライブラリに追加するメソッドなど
    public void addMovieToLibrary(Movie movie) {
        // 追加処理
    }

    // 映画を削除するメソッド
    public void removeMovieFromLibrary(int movieId) {
        // 削除処理
    }

    // ゲッターとセッター
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
}

