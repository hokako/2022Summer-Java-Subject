package util;

import java.util.Random;

/**
 * @name ID生成类
 * @author 高杰
 * @date 2022-08-14
 * @description 生成指定长度的ID
 */
public class IDUtil {

    private static char[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * 生成指定长度的ID
     *
     * @param length
     * @return
     */
    public static String generateId(int length) {
        Random r = new Random();
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            int index = r.nextInt(characters.length);
            builder.append(characters[index]);

        }
        return builder.toString();
    }

}