package command;

import dao.FilmManager;
import entity.Film;
import util.DateUtil;
import util.InputUtil;

/**
 * @name 管理员行为类
 * @author 高杰、胡启坤、杨帆、李佳仪、祝紫璇
 * @date 2022-08-18
 * @description 增加影片、删除影片、更新影片
 */
public class ManagerAction {
    // 增加影片
    public static void createFilm() {
        String name = InputUtil.getInputText("请输入电影的名称：");
        // 判断电影是否已存在
        if (FilmManager.containFilm(name)) {
            System.out.println("该电影已存在");
            return;
        }
        String strReleaseDate = InputUtil.getInputDate("请按照格式" + FilmManager.ft1 + "输入上映日期", FilmManager.ft1);
        String strOfflineDate = InputUtil.getInputDate("请按照格式" + FilmManager.ft1 + "输入下架日期", FilmManager.ft1);
        String strBeginDate = InputUtil.getInputDate("请按照格式" + FilmManager.ft2 + "输入开始时间", FilmManager.ft2);
        String strEndDate = InputUtil.getInputDate("请按照格式" + FilmManager.ft2 + "输入结束时间", FilmManager.ft2);
        Film film = new Film(name, DateUtil.createFormatDate(strReleaseDate, FilmManager.ft1),
                DateUtil.createFormatDate(strOfflineDate, FilmManager.ft1),
                DateUtil.createFormatDate(strBeginDate, FilmManager.ft2),
                DateUtil.createFormatDate(strEndDate, FilmManager.ft2));
        // 判断开始时间结束时间是否冲突
        if (FilmManager.timeConflict(film)) {
            System.out.println("该电影与其他电影时间冲突");
            return;
        }
        System.out.println("添加" + name + "影片成功");
        FilmManager.addFilm(film);
    }

    // 删除影片
    public static void deleteFilm() {
        String id = InputUtil.getInputSet("请选择要删除的影片编号：", FilmManager.getFilmIDSet());
        Film film = FilmManager.getFilm(id);
        String name = film.getName();
        int choice = InputUtil.getInputInteger("确认要删除电影《" + name + "》吗？（请按1进行确认，否则请按0）", 0, 1);
        if (choice == 1) {
            FilmManager.deleteFilm(id);
            System.out.println("删除电影《" + name + "》成功！");
        } else {
            System.out.println("取消删除\n");
        }
    }

    // 更新影片
    public static void updateFilm() {
        String id = InputUtil.getInputSet("请选择要更新的影片编号：", FilmManager.getFilmIDSet());
        Film film = FilmManager.getFilm(id);
        boolean flag = true;
        System.out.println("请选择要更新的信息：\n1:名称\n2:上映日期\n3:下线日期4:播放时间\n5:结束时间\0:退出");
        while (flag) {
            int num = InputUtil.getInputInteger("请输入0~5之间的整数：", 0, 5);
            switch (num) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    String newName = InputUtil.getInputText("请输入新的名称：");
                    film.setName(newName);
                    break;
                case 2:
                    String strReleaseDate = InputUtil.getInputDate("请按照格式" + FilmManager.ft1 + "输入上映日期", FilmManager.ft1);
                    film.setReleaseDate(DateUtil.createFormatDate(strReleaseDate, FilmManager.ft1));
                    break;
                case 3:
                    String strOfflineDate = InputUtil.getInputDate("请按照格式" + FilmManager.ft1 + "输入下架日期", FilmManager.ft1);
                    film.setReleaseDate(DateUtil.createFormatDate(strOfflineDate, FilmManager.ft1));
                    break;
                case 4:
                    String strBeginDate = InputUtil.getInputDate("请按照格式" + FilmManager.ft2 + "输入开始时间", FilmManager.ft2);
                    film.setBeginTime(DateUtil.createFormatDate(strBeginDate, FilmManager.ft2));
                    break;
                case 5:
                    String strEndDate = InputUtil.getInputDate("请按照格式" + FilmManager.ft2 + "输入结束时间", FilmManager.ft2);
                    film.setEndTime(DateUtil.createFormatDate(strEndDate, FilmManager.ft2));
                    break;
                default:
                    break;
            }
        }
        FilmManager.updateFilm(film.getId(), film);
    }

}
