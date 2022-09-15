package entity;

import util.IDUtil;

import java.io.Serializable;

/**
 * @name 影厅类
 * @author 胡启坤、杨帆、李佳仪
 * @date 2022-08-12
 * @description 影厅拥有编号，排数，列数，座位列表。其中编号唯一，排数和列数确定座位列表的大小，座位列表主要用于展示和计算余票，方便观众选购座位。
 */
public class Cinema implements Serializable {
    // 影厅编号
    private String id;
    // 座位排数
    private int totalRow;
    // 座位列数
    private int totalColumn;
    // 座位列表
    private Seat[][] allSeat;

    // 构造方法
    public Cinema(int totalRow, int totalColumn) {
        this.id = IDUtil.generateId(4);
        this.totalColumn = totalColumn;
        this.totalRow = totalRow;
        this.allSeat = new Seat[totalRow][totalColumn];
        for (int i = 0; i < totalRow; i++) {
            for (int j = 0; j < totalColumn; j++) {
                allSeat[i][j] = new Seat(i, j, null);
            }
        }
    }

    @Override
    public String toString() {
        String seat = "";
        for (int i = 0; i < totalRow; i++) {
            for (int j = 0; j < totalColumn; j++) {
                seat += allSeat[i][j].getUser() == null ? "□" : "■";
            }
            if (i != totalRow - 1) {
                seat += "\n";
            }
        }
        return id + "影厅(□代表空座，■代表非空座)\n" + seat;
    }

    //Getter and Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getTotalColumn() {
        return totalColumn;
    }

    public void setTotalColumn(int totalColumn) {
        this.totalColumn = totalColumn;
    }

    public Seat[][] getAllSeat() {
        return allSeat;
    }

    public void setAllSeat(Seat[][] allSeat) {
        this.allSeat = allSeat;
    }
}
