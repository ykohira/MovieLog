package dto;

public class TagDTO {

    private int id;  // id フィールド
    private String name;  // タグ名
    private int userId;  // ユーザーID（新しく追加）

    // デフォルトコンストラクタ
    public TagDTO() {
    }

    // コンストラクタ
    public TagDTO(int id, String name, int userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    // ゲッターとセッター
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TagDTO [id=" + id + ", name=" + name + ", userId=" + userId + "]";
    }
}
