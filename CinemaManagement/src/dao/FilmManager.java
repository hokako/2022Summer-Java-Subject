package dao;

import entity.Film;
import util.JdbcUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 * @author 高杰、胡启坤、祝紫璇
 * @name 影片管理类
 * @date 2022-08-30
 * @description 日期格式、影片数据、获取影片ID集合、展示所有电影、添加电影、删除电影、修改电影、
 * 查询名称为 name的电影是否存在、返回 film实例和判断影片是否时间冲突
 */
public class FilmManager {
    // 上映时间和下架时间格式
    public static String ft1 = "yyyy-MM-dd";

    // 开始时间和结束时间格式
    public static String ft2 = "yyyy-MM-dd hh:mm";

    private static HashMap<String, Film> filmData = new HashMap<>();

    // 静态代码块预加载
    static {
//        Film film1 = new Film("星际穿越", DateUtil.createFormatDate(FilmManager.ft1, "2014-11-5"),
//                null, DateUtil.createFormatDate(FilmManager.ft2, "2022-09-01 8:00"),
//                DateUtil.createFormatDate(FilmManager.ft2, "2022-09-01 10:00"));
//        Film film2 = new Film("肖申克的救赎", DateUtil.createFormatDate(FilmManager.ft1, "1994-9-10"),
//                null, DateUtil.createFormatDate(FilmManager.ft2, "2022-09-01 10:10"),
//                DateUtil.createFormatDate(FilmManager.ft2, "2022-09-01 12:10"));
//        FilmManager.addFilm(film1);
//        FilmManager.addFilm(film2);
//        filmData.forEach((s, film) ->
//                JdbcUtil.update("insert into film values(?)", film)
//        );
        JdbcUtil.query("select film from film;", Film.class).forEach((FilmManager::addFilm));
    }

    /* 获取影片ID集合 */
    public static Set<String> getFilmIDSet() {
        return filmData.keySet();
    }

    /* 展示所有电影 */
    public static void showFilm() {
        System.out.println("编号\t名称\t上映日期\t下架日期\t开始时间\t结束时间\t");
        for (Film film : filmData.values()) {
            System.out.println(film);
        }
    }

    /* 添加电影 */
    public static void addFilm(Film film) {
        filmData.put(film.getId(), film);
        updateFilmTable();
    }

    /* 删除电影 */
    public static void deleteFilm(String id) {
        filmData.remove(id);
        updateFilmTable();
    }

    /* 修改电影 */
    public static void updateFilm(String id, Film film) {
        filmData.replace(id, film);
        updateFilmTable();
    }

    /* 查询名称为 name的电影是否存在 */
    public static boolean containFilm(String name) {
        for (Film film : filmData.values()) {
            if (name == film.getName()) {
                return true;
            }
        }
        return false;
    }

    /* 返回 film实例 */
    public static Film getFilm(String id) {
        return filmData.get(id);
    }

    /* 判断影片是否时间冲突 */
    //将film的开始时间与结束时间与所有的现存影片遍历比较如果冲突，返回true，否则，返回false
    public static boolean timeConflict(Film film) {
        //得到当前film放映的开始时间和结束时间
        Date filmBeginTime = film.getBeginTime();
        Date filmEndTime = film.getEndTime();
        //冲突标志
        boolean conflict = false;
        //遍历影片集合
        for (String key : filmData.keySet()) {
            //集合中的一个影片的放映的开始时间和结束时间
            Film hadFilm = filmData.get(key);
            Date hadFilmBeginTime = hadFilm.getBeginTime();
            Date hadFilmEndTime = hadFilm.getEndTime();
            /* 1.filmEndTime < hadFilmBeginTime,不冲突
             * 2.filmBeginTime > hadFilmEndTime,不冲突
             * 3.其它，冲突
             * 由此可得1和2是或关系*/
            if ((filmEndTime.compareTo(hadFilmBeginTime) < 0) || (filmBeginTime.compareTo(hadFilmEndTime) > 0)) {
                continue;//不冲突，结束本轮循环，进行下一轮循环
            } else {
                conflict = true;//冲突，跳出循环
                break;
            }

        }

        return conflict;
    }

    // 数据库更新
    private static void updateFilmTable() {
        JdbcUtil.update("truncate film;");
        filmData.forEach((s, film) ->
                JdbcUtil.update("insert into film values(?)", film)
        );
    }
}
