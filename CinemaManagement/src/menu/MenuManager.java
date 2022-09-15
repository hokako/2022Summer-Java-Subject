package menu;

import command.Command;

import java.util.stream.Stream;

/**
 * @name 菜单管理类
 * @author 高杰
 * @date 2022-08-14
 * @description 菜单管理拥有静态菜单，和静态展示给定菜单方法
 */
public class MenuManager {
    /*** 登录菜单 */
    public static final Menu[] LOGIN_MENUS = {
            new Menu(1, "登录", Command.LOGIN),
            new Menu(2, "退出", Command.QUIT),
    };
    /*** 观众菜单 */
    public static final Menu[] AUDIENCE_MENUS = {
            new Menu(1, "查看影片", Command.SHOW_FILM),
            new Menu(2, "返回登录菜单", Command.GO_BACK_LOGIN)
    };
    /*** 影片菜单 */
    public static final Menu[] FILM_MENUS = {
            new Menu(1, "选择影片", Command.SELECT_FILM),
            new Menu(2, "返回观众菜单", Command.GO_BACK_AUDIENCE),
    };
    /*** 影厅菜单 */
    public static final Menu[] CINEMA_MENUS = {
            new Menu(1, "选择影厅", Command.SELECT_CINEMA),
            new Menu(2, "返回影片菜单", Command.GO_BACK_FILM)
    };
    /*** 座位菜单 */
    public static final Menu[] SEAT_MENUS = {
            new Menu(1, "自动订座", Command.AUTO_SEAT),
            new Menu(2, "手动订座", Command.SELF_SEAT),
            new Menu(3, "返回影厅菜单", Command.GO_BACK_CINEMA)
    };
    /*** 管理员菜单 */
    public static final Menu[] MANAGER_MENUS = {
            new Menu(1, "影片管理", Command.FILM_MANAGE),
            new Menu(2, "返回登录菜单", Command.GO_BACK_LOGIN)
    };
    /*** 影院管理菜单 */
    public static final Menu[] FILM_MANAGE_MENUS = {
            new Menu(1, "增加影片", Command.CREATE_FILM),
            new Menu(2, "删除影片", Command.DELETE_FILM),
            new Menu(3, "修改影片", Command.UPDATE_FILM),
            new Menu(4, "返回管理员菜单", Command.GO_BACK_MANAGER)
    };

    /**
     * 展示给定的菜单数组
     *
     * @param menus
     */
    public static void showMenu(Menu[] menus) {
        System.out.println("==========================");
        Stream.of(menus).forEach(System.out::println);
        System.out.println("==========================");
    }

}
