package com.biang.www.po;

/**
 * @author Biang
 */
public class User {
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User(int userId, String userName, String password, int level) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.level = level;
    }

    public User(){};
    private int userId;
    private String userName;
    private String password;
    private int level;
}
