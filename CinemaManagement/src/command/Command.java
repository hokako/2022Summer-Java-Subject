package command;

/**
 * @name 命令接口
 * @author 高杰、胡启坤、杨帆、李佳仪、祝紫璇
 * @date 2022-08-18
 * @description 命令所对应的序号
 */
public interface Command {
    // 返回登录
    int GO_BACK_LOGIN = 0x00;
    // 登录
    int LOGIN = 0x01;
    // 退出
    int QUIT = 0x02;
    // 查看影片
    int SHOW_FILM = 0x03;
    // 返回观众菜单
    int GO_BACK_AUDIENCE = 0x04;
    // 选择影片
    int SELECT_FILM = 0x05;
    // 选择影厅
    int SELECT_CINEMA = 0x06;
    // 返回影片菜单
    int GO_BACK_FILM = 0x07;
    // 自动订座
    int AUTO_SEAT = 0x08;
    // 手动订座
    int SELF_SEAT = 0x09;
    // 返回影厅菜单
    int GO_BACK_CINEMA = 0x0a;
    // 影片管理
    int FILM_MANAGE = 0x0b;
    // 增加影片
    int CREATE_FILM = 0x0c;
    // 删除影片
    int DELETE_FILM = 0x0d;
    // 修改影片
    int UPDATE_FILM = 0x0e;
    // 返回管理员菜单
    int GO_BACK_MANAGER = 0x0f;
}
