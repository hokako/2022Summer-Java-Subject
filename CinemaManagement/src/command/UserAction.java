package command;

import dao.CinemaManager;
import dao.FilmManager;
import dao.UserManager;
import entity.User;
import util.InputUtil;

/**
 * @name 用户行为类
 * @author 高杰、胡启坤、杨帆、李佳仪、祝紫璇
 * @date 2022-08-18
 * @description 登录、展示影片、展示影厅
 */
public class UserAction {

    public static User user;

    // 展示影片
    public static void showFilm() {
        FilmManager.showFilm();
    }

    // 展示影厅
    public static void showCinema() {
        CinemaManager.showCinema();
    }

    // 登录
    public static int login() {
        // 账号
        String account = InputUtil.getInputText("请输入账号:");
        // 密码
        String password = InputUtil.getInputText("请输入密码:");
        // 登录验证
        if (UserManager.containsUser(account) && password.equals(UserManager.userPassword(account))) {
            user = UserManager.getUser(account);
            return user.getRole();
        } else {
            System.out.println("账号或密码错误！");
            return -1;
        }
    }
}
