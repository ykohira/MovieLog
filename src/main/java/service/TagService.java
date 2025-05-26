package service;

import java.sql.SQLException;

import dao.TagDAO;
import dto.TagDTO;

public class TagService {

    public int registerTag(String tagName, int userId) throws SQLException {
        TagDAO tagDAO = new TagDAO();

        if (tagName == null || tagName.trim().isEmpty()) {
            throw new IllegalArgumentException("タグ名が未入力です");
        }

        // 重複チェックをここで実施！ ユーザーIDを追加
        if (tagDAO.isTagNameExist(tagName.trim(), userId)) {
            throw new IllegalArgumentException("同じ名前のタグが既に存在します");
        }

        // DTO作成して登録
        TagDTO tag = new TagDTO();
        tag.setName(tagName.trim());
        tag.setUserId(userId);  // ユーザーIDを設定
        return tagDAO.addTag(tag);
    }
}
