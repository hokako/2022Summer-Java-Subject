package command;

import dao.CinemaManager;
import dao.FilmManager;
import entity.Cinema;
import entity.Film;
import entity.Seat;
import util.InputUtil;

/**
 * @author 高杰、胡启坤、杨帆、李佳仪、祝紫璇
 * @name 观众行为类
 * @date 2022-08-18
 * @description 选择影片、选择影厅、自动选座、手动选择
 */
public class AudienceAction {

    // 记录选择的 Film
    public static Film film;

    // 记录选择的 Cinema
    public static Cinema cinema;

    // 选择影片
    public static void selectFilm() {
        String id = InputUtil.getInputSet("请选择影片编号：", FilmManager.getFilmIDSet());
        film = FilmManager.getFilm(id);
        System.out.println("已选择" + film.getName() + "影片");
    }

    // 选择影厅
    public static boolean selectCinema() {
        CinemaManager.showCinema();
        String id = InputUtil.getInputSet("请选择影厅编号：", CinemaManager.getCinemaIDSet());
        cinema = CinemaManager.getCinema(id);
        // 判断座位是否已满
        Seat seat = CinemaManager.getEmptySeat(cinema);
        if (seat == null) {
            System.out.println(cinema.getId() + "影厅已满座，请选择其他影厅");
            return false;
        }
        System.out.println("已选择" + cinema.getId() + "影厅");
        return true;
    }

    // 自动选座
    public static void autoSeat() {
        Seat seat = CinemaManager.getEmptySeat(cinema);
        seat.setUser(UserAction.user);
        CinemaManager.bookSeat(film, cinema, seat);
    }

    // 手动选择
    public static void selfSeat() {
        System.out.println(cinema);
        Seat[][] allSeat = cinema.getAllSeat();
        int totalRow = cinema.getTotalRow();
        int totalColumn = cinema.getTotalColumn();
        while (true) {
            int row = InputUtil.getInputInteger("请输入排数：", 1, totalRow);
            int column = InputUtil.getInputInteger("请输入列数：", 1, totalColumn);
            if (allSeat[row - 1][column - 1].getUser() == null) {
                Seat seat = new Seat(row - 1, column - 1, UserAction.user);
                CinemaManager.bookSeat(film, cinema, seat);
                break;
            } else {
                System.out.println("此位置已被他人预定，请重新选择");
            }
        }
    }
}
