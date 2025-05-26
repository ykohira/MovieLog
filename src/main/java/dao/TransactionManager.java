package dao;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private Connection conn;
    private boolean isCommit;

    // コンストラクタでデータベース接続情報を受け取ってフィールドで保持
    public TransactionManager(Connection conn) {
        this.conn = conn;
        try {
            // 自動コミットモードをオフに設定（トランザクションの開始）
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("トランザクション開始時にエラーが発生しました。", e);
        }
    }

    // トランザクションをコミット（＝処理を確定させる）
    public void commit() throws SQLException {
        if (conn == null) {
            throw new SQLException("トランザクションが開始されていません");
        }
        try {
            conn.commit(); // コミットを実行
            isCommit = true; // コミット済みのフラグを設定
        } catch (SQLException e) {
            rollback(); // コミット失敗時はロールバック
            throw e; // 例外をスローして処理を上に伝播
        }
    }

    // トランザクションをロールバック
    public void rollback() throws SQLException {
        if (conn == null) {
            throw new SQLException("トランザクションが開始されていません");
        }
        try {
            conn.rollback(); // ロールバックを実行
            isCommit = false; // ロールバック済みのフラグを設定
        } catch (SQLException e) {
            throw new SQLException("ロールバックに失敗しました", e);
        }
    }

    // トランザクションを終了し、接続を閉じる
    public void close() {
        try {
            if (conn != null) {
                if (isCommit) {
                    conn.commit(); // コミットが指示されていればコミット
                } else {
                    conn.rollback(); // そうでなければロールバック
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("トランザクション終了時にエラーが発生しました", e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close(); // 最後に接続を閉じる
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("接続を閉じる際にエラーが発生しました", e);
            } finally {
                conn = null; // 明示的にnullをセットしてGCされるようにする
            }
        }
    }
}
