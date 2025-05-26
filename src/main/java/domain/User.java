package domain;

import java.sql.Timestamp;

public class User {
    private int id;
    private String userName;
    private String password;
    private String email;
    private Timestamp createdAt;

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    
    public User(int id, String userName, String password, String email, Timestamp createdAt) {
    	this.id = id;
    	this.userName = userName;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
    }

    // ログイン処理
    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    // メールのフォーマットチェック
    public boolean isValidEmail() {
        return email != null && email.contains("@");
    }

    // ゲッターとセッター
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
