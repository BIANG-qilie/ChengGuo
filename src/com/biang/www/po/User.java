package com.biang.www.po;

/**
 * @author Biang
 */
public class User {
    public static final int COMMON_USER=1;
    public static final int ENTERPRISE_USER=2;
    public static final int MANAGER=3;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(int userId, String userName, String password, int level) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.level = level;
    }

    public User(){}

    private int userId;
    private String userName;
    private String password;
    private String email;
    private int level;

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    private String headImage;
    @Override
    public String toString(){
        return "用户ID:"+userId+" \n"+
                "用户名:"+userName+" \n"+
                "密码:"+password+" \n"+
                "用户等级:"+level+" \n"+
                "用户邮箱:"+email+" \n";
    }
}
