package entity;

import java.io.Serializable;

/**
 * @author 胡启坤、杨帆、李佳仪
 * @name 用户类
 * @date 2022-08-12
 * @description 用户分为管理员和观众。用户拥有账号，密码和角色。其中账号唯一，账号和密码用于登录，角色用来区分管理员和观众。
 */
public class User implements Serializable {
    // 账号
    private String account;
    // 密码
    private String password;
    // 用户角色，1为管理员，0为观众
    private int role;

    // 构造方法
    public User(String account, String password) {
        this.account = account;
        this.password = password;
        this.role = 0;
    }

    public User(String account, String password, int role) {
        this.account = account;
        this.password = password;
        this.role = role;
    }

    // Getter and Setter
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
