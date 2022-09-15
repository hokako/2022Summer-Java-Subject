import command.AudienceAction;
import command.Command;
import command.ManagerAction;
import command.UserAction;
import menu.Menu;
import menu.MenuManager;
import util.InputUtil;

/**
 * @name 启动类
 * @author 高杰
 * @date 2022-08-15
 * @description 程序入口，递归展示菜单界面
 */
public class Launcher {

    public static void main(String[] args) {
        showInterface(MenuManager.LOGIN_MENUS);
    }

    /**
     * 递归展示菜单界面
     *
     * @param menus
     */
    public static void showInterface(Menu[] menus) {
        MenuManager.showMenu(menus);
        int number = InputUtil.getInputInteger("请选择菜单编号：", 1, menus.length);
        Menu select = menus[number - 1];
        switch (select.getCommand()) {
            case Command.GO_BACK_LOGIN:
                System.out.println("你选择了返回登录");
                showInterface(MenuManager.LOGIN_MENUS);
                break;
            case Command.LOGIN:
                System.out.println("你选择了登录");
                int role = UserAction.login();
                if (role == 0) {
                    showInterface(MenuManager.AUDIENCE_MENUS);
                } else if (role == 1) {
                    showInterface(MenuManager.MANAGER_MENUS);
                } else {
                    showInterface(MenuManager.LOGIN_MENUS);
                }
                break;
            case Command.QUIT:
                System.out.println("你选择了退出");
                System.exit(0);
                break;
            case Command.SHOW_FILM:
                System.out.println("你选择了查看影片");
                UserAction.showFilm();
                showInterface(MenuManager.FILM_MENUS);
                break;
            case Command.GO_BACK_AUDIENCE:
                System.out.println("你选择了返回观众菜单");
                showInterface(MenuManager.AUDIENCE_MENUS);
                break;
            case Command.SELECT_FILM:
                System.out.println("你选择了选择影片");
                AudienceAction.selectFilm();
                showInterface(MenuManager.CINEMA_MENUS);
                break;
            case Command.SELECT_CINEMA:
                System.out.println("你选择了选择影厅");
                boolean flag = AudienceAction.selectCinema();
                if (flag) {
                    showInterface(MenuManager.SEAT_MENUS);
                } else {
                    showInterface(MenuManager.CINEMA_MENUS);
                }
                break;
            case Command.GO_BACK_FILM:
                System.out.println("你选择了返回影片菜单");
                showInterface(MenuManager.FILM_MENUS);
                break;
            case Command.AUTO_SEAT:
                System.out.println("你选择了自动选座");
                AudienceAction.autoSeat();
                showInterface(MenuManager.AUDIENCE_MENUS);
                break;
            case Command.SELF_SEAT:
                System.out.println("你选择了手动选座");
                AudienceAction.selfSeat();
                showInterface(MenuManager.AUDIENCE_MENUS);
                break;
            case Command.GO_BACK_CINEMA:
                System.out.println("你选择了返回影厅菜单");
                showInterface(MenuManager.CINEMA_MENUS);
                break;
            case Command.FILM_MANAGE:
                System.out.println("你选择了影片管理");
                UserAction.showFilm();
                showInterface(MenuManager.FILM_MANAGE_MENUS);
                break;
            case Command.CREATE_FILM:
                System.out.println("你选择了增加影片");
                ManagerAction.createFilm();
                showInterface(MenuManager.FILM_MANAGE_MENUS);
                break;
            case Command.DELETE_FILM:
                System.out.println("你选择了删除影片");
                ManagerAction.deleteFilm();
                showInterface(MenuManager.FILM_MANAGE_MENUS);
                break;
            case Command.UPDATE_FILM:
                System.out.println("你选择了修改影片");
                ManagerAction.updateFilm();
                showInterface(MenuManager.FILM_MANAGE_MENUS);
                break;
            case Command.GO_BACK_MANAGER:
                System.out.println("你选择了返回影片管理菜单");
                showInterface(MenuManager.FILM_MANAGE_MENUS);
                break;
            default:
                System.out.println(select);
                showInterface(menus);
        }
    }
}
