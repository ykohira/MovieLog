package domain;

public class Movie {
    private String title;
    private String genre;
    private String description;
    private String tmdbId;
    private String thumbnailUrl;

    public Movie(String title, String genre, String description, String tmdbId, String thumbnailUrl) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.tmdbId = tmdbId;
        this.thumbnailUrl = thumbnailUrl;
    }

    // 映画の評価を追加するメソッドなどを追加可能
    public String getDisplayInfo() {
        return String.format("Title: %s, Genre: %s, Description: %s", title, genre, description);
    }

    // ゲッターとセッター
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getTmdbId() { return tmdbId; }
    public void setTmdbId(String tmdbId) { this.tmdbId = tmdbId; }
    public String getThumbnailUrl() { return thumbnailUrl; }
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }
}

