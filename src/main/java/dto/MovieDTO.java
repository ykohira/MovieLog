package dto;

public class MovieDTO {
    private String title;
    private String genre;
    private String description;
    private int tmdbId;
    private String thumbnailUrl;

    // コンストラクタ
    public MovieDTO(String title, String genre, String description, int tmdbId, String thumbnailUrl) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.tmdbId = tmdbId;
        this.thumbnailUrl = thumbnailUrl;
    }

    public MovieDTO() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	// ゲッターとセッター
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(int tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}

