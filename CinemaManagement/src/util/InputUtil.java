package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Set;

/**
 * @name 系统输入类
 * @author 高杰
 * @date 2022-08-14
 * @description 系统输入包括从控制台获取一个给定范围区间内的整数、从控制台获取一个字符串、
 *              从控制台获取一个在 set里的字符串和从控制台获取一个给定日期格式的日期
 */
public class InputUtil {

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * 从控制台获取一个给定范围区间内的整数
     *
     * @param tip
     * @param min
     * @param max
     * @return
     */
    public static int getInputInteger(String tip, int min, int max) {
        System.out.println(tip);
        while (true) {
            if (SCANNER.hasNextInt()) {
                int number = SCANNER.nextInt();
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println("输入错误，请输入" + min + "~" + max + "之间的整数");
                }
            } else {
                System.out.println("输入错误，请输入" + min + "~" + max + "之间 的整数");
                SCANNER.next();
            }
        }
    }

    /**
     * 从控制台获取一个字符串
     *
     * @param tip
     * @return
     */
    public static String getInputText(String tip) {
        System.out.println(tip);
        return SCANNER.next();
    }

    /**
     * 从控制台获取一个在 set里的字符串
     *
     * @param tip
     * @param set
     * @return
     */
    public static String getInputSet(String tip, Set<String> set) {
        System.out.println(tip);
        while (true) {
            if (SCANNER.hasNext()) {
                String str = SCANNER.next();
                if (set.contains(str)) {
                    return str;
                } else {
                    System.out.println("输入错误，请在" + set + "中选择输入");
                }
            } else {
                System.out.println("输入错误，请在" + set + "中选择输入");
                SCANNER.next();
            }
        }
    }

    /**
     * 从控制台获取一个给定日期格式的日期
     *
     * @param tip
     * @param format
     * @return
     */
    public static String getInputDate(String tip, String format) {
        System.out.println(tip);
        while (true) {
            String dateStr = SCANNER.next();
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            boolean valid = true;
            try {
                sdf.parse(dateStr);
            } catch (ParseException e) {
                valid = false;
            }
            if (valid) {
                return dateStr;
            } else {
                System.out.println("日期格式错误，请重新输入：（" + format + "）");
            }
        }
    }
}
