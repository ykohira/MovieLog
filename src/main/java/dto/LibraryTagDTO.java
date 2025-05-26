package dto;

public class LibraryTagDTO {

    private int libraryId;
    private int tagId;

    // コンストラクタ
    public LibraryTagDTO(int libraryId, int tagId) {
        this.libraryId = libraryId;
        this.tagId = tagId;
    }

    // ゲッターとセッター
    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "LibraryTagDTO [libraryId=" + libraryId + ", tagId=" + tagId + "]";
    }
}
