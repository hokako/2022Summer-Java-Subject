package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @name 日期功能类类
 * @author 高杰
 * @date 2022-08-14
 * @description 格式化日期、根据格式化日期创建 Date对象
 */
public class DateUtil {

    // 格式化日期
    public static String dateFormat(Date date, String pattern) {
        if (date == null) return "--";
        SimpleDateFormat ft = new SimpleDateFormat(pattern);
        return ft.format(date);
    }

    // 根据格式化日期创建 Date对象
    public static Date createFormatDate(String pattern, String date) {
        SimpleDateFormat ft = new SimpleDateFormat(pattern);
        try {
            return ft.parse(date);
        } catch (ParseException e) {
            System.out.println("日期格式解析失败");
            return null;
        }
    }
}
