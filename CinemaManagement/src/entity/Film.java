package entity;


import dao.FilmManager;
import util.DateUtil;
import util.IDUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * @name 影片类
 * @author 胡启坤、杨帆、李佳仪
 * @date 2022-08-12
 * @description 影片拥有编号，名称，上映时间，下架时间，播放时间和结束时间。其中编号唯一
 */
public class Film implements Serializable {
    // 影片编号
    private String id;
    // 影片名称
    private String name;
    // 上映日期
    private Date releaseDate;
    // 下线日期
    private Date offlineDate;
    // 开始时间
    private Date beginTime;
    // 结束时间
    private Date endTime;

    //构造方法
    public Film(String name, Date releaseDate, Date offlineDate) {
        this.id = IDUtil.generateId(4);
        this.name = name;
        this.releaseDate = releaseDate;
        this.offlineDate = offlineDate;
        this.beginTime = null;
        this.endTime = null;
    }

    public Film(String name, Date releaseDate, Date offlineDate, Date beginTime, Date endTime) {
        this.id = IDUtil.generateId(4);
        this.name = name;
        this.releaseDate = releaseDate;
        this.offlineDate = offlineDate;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return id + "\t" + name + "\t" + DateUtil.dateFormat(releaseDate, FilmManager.ft1) + "\t" +
                DateUtil.dateFormat(offlineDate, FilmManager.ft1) + "\t" +
                DateUtil.dateFormat(beginTime, FilmManager.ft2) + "\t" +
                DateUtil.dateFormat(endTime, FilmManager.ft2) + "\t";
    }


    //Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getOfflineDate() {
        return offlineDate;
    }

    public void setOfflineDate(Date offlineDate) {
        this.offlineDate = offlineDate;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
