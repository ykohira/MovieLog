package dto;

public class WatchlistTagDTO {

    private int watchlistId;
    private int tagId;

    // コンストラクタ
    public WatchlistTagDTO(int watchlistId, int tagId) {
        this.watchlistId = watchlistId;
        this.tagId = tagId;
    }

    // ゲッターとセッター
    public int getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(int watchlistId) {
        this.watchlistId = watchlistId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "WatchlistTagDTO [watchlistId=" + watchlistId + ", tagId=" + tagId + "]";
    }
}
