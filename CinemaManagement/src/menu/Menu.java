package menu;

/**
 * @name 菜单类
 * @author 高杰
 * @date 2022-08-14
 * @description 菜单拥有序号，名称和菜单命令。
 */
public class Menu {
    // 序号
    private int order;
    // 名称
    private String name;
    // 菜单命令
    private int command;

    public Menu(int order, String name, int command) {
        this.order = order;
        this.name = name;
        this.command = command;
    }

    @Override
    public String toString() {
        return order + "." + name;
    }

    // Getter and Setter
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }
}
