package entity;

import java.io.Serializable;

/**
 * @name 座位类
 * @author 胡启坤、杨帆、李佳仪
 * @date 2022-08-12
 * @description 座位拥有排号、列号和所属用户。排号和列号决定座位所处位置，所属用户用来判定座位是否被订购。
 */
public class Seat implements Serializable {
    // 排号
    private int row;
    // 列号
    private int column;
    // 所属用户
    private User user;

    // 构造函数
    public Seat(int row, int column, User user) {
        this.row = row;
        this.column = column;
        this.user = user;
    }

    // Getter and Setter
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
