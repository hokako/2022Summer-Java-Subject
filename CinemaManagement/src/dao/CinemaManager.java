package dao;

import entity.Cinema;
import entity.Film;
import entity.Seat;
import util.DateUtil;
import util.JdbcUtil;

import java.util.HashMap;
import java.util.Set;

/**
 * @author 高杰、胡启坤、祝紫璇
 * @name 影厅管理类
 * @date 2022-08-30
 * @description 影厅数据、展示影院、增加影院、修改影院、影厅ID集合、获取 cinema实例 、返回 cinema的一个空座、订座
 */
public class CinemaManager {

    private static HashMap<String, Cinema> cinemaData = new HashMap<>();

    // 静态代码块预加载
    static {
//        Cinema cinema1 = new Cinema(3, 4);
//        Cinema cinema2 = new Cinema(3, 4);
//        CinemaManager.addCinema(cinema1);
//        CinemaManager.addCinema(cinema2);
//        cinemaData.forEach((s, cinema) ->
//                JdbcUtil.update("insert into cinema values(?)", cinema)
//        );
        JdbcUtil.query("select cinema from cinema;", Cinema.class).forEach((CinemaManager::addCinema));
    }

    /* 展示影院 */
    public static void showCinema() {
        for (Cinema cinema : cinemaData.values()) {
            System.out.println(cinema);
        }
    }

    /* 增加影院 */
    public static void addCinema(Cinema cinema) {
        cinemaData.put(cinema.getId(), cinema);
        updateCinemaTable();
    }

    /* 修改影院 */
    public static void updateCinema(String id, Cinema cinema) {
        cinemaData.replace(id, cinema);
        updateCinemaTable();
    }

    /* 影厅ID集合 */
    public static Set<String> getCinemaIDSet() {
        return cinemaData.keySet();
    }

    /* 获取 cinema实例 */
    public static Cinema getCinema(String id) {
        return cinemaData.get(id);
    }

    /* 返回 cinema的一个空座 */
    public static Seat getEmptySeat(Cinema cinema) {
        Seat[][] allSeat = cinema.getAllSeat();
        int totalRow = cinema.getTotalRow();
        int totalColumn = cinema.getTotalColumn();
        for (int i = 0; i < totalRow; i++) {
            for (int j = 0; j < totalColumn; j++) {
                if (allSeat[i][j].getUser() == null) {
                    return allSeat[i][j];
                }
            }
        }
        return null;
    }

    /* 订座 */
    public static void bookSeat(Film film, Cinema cinema, Seat seat) {
        Seat[][] allSeat = cinema.getAllSeat();
        int row = seat.getRow();
        int column = seat.getColumn();
        allSeat[row][column] = seat;
        cinema.setAllSeat(allSeat);
        CinemaManager.updateCinema(cinema.getId(), cinema);
        System.out.println("订票成功，信息如下：");
        System.out.println(film.getName() + "\t" + DateUtil.dateFormat(film.getBeginTime(), FilmManager.ft2)
                + " - " + DateUtil.dateFormat(film.getEndTime(), FilmManager.ft2));
        System.out.println(cinema.getId() + "影厅" + "\t" + (row + 1) + "行" + (column + 1) + "列");
        updateCinemaTable();
    }

    // 数据库更新
    private static void updateCinemaTable() {
        JdbcUtil.update("truncate cinema;");
        cinemaData.forEach((s, cinema) ->
                JdbcUtil.update("insert into cinema values(?)", cinema)
        );
    }

}
