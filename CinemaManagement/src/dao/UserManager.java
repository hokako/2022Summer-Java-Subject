package dao;

import entity.User;
import util.JdbcUtil;

import java.util.HashMap;

/**
 * @author 高杰、胡启坤、祝紫璇
 * @name 用户管理类
 * @date 2022-08-30
 * @description 用户数据、添加用户、删除用户、查询账号为 account的用户是否存在、返回 account的密码、返回账号为 account的用户 User
 */
public class UserManager {

    private static HashMap<String, User> userData = new HashMap<>();

    // 静态代码块预加载
    static {
//        User admin = new User("admin", "123", 1);
//        User audi = new User("audi", "123", 0);
//        UserManager.addUser(admin);
//        UserManager.addUser(audi);
//        userData.forEach((s, user) ->
//                JdbcUtil.update("insert into user values(?)", user)
//        );
        JdbcUtil.query("select user from user;", User.class).forEach((UserManager::addUser));
    }

    /* 添加用户 */
    public static void addUser(User user) {
        userData.put((user.getAccount()), user);
        updateUserTable();
    }

    /* 删除用户 */
    public static void deleteUser(String account) {
        userData.remove(account);
        updateUserTable();
    }

    /* 查询账号为 account的用户是否存在 */
    public static boolean containsUser(String account) {
        return userData.containsKey(account);
    }

    /* 返回 account的密码 */
    public static String userPassword(String account) {
        return userData.get(account).getPassword();
    }

    /* 返回账号为 account的用户 User */
    public static User getUser(String account) {
        return userData.get(account);
    }

    // 数据库更新
    private static void updateUserTable() {
        JdbcUtil.update("truncate user;");
        userData.forEach((s, user) ->
                JdbcUtil.update("insert into user values(?)", user)
        );
    }
}
