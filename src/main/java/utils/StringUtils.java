package utils;

import java.util.Random;

/**
 * 字符串工具类
 */
public class StringUtils {
    /**
     * 是否为空
     */
    public static boolean isEmpty(Object o){
        if(o==null)return true;
        return "".equals(o);
    }
     
    /**
     * 是否不为空
     */
    public static boolean isNotEmpty(Object o){
        return !isEmpty(o);
    }

    /**
     * 随机生成10位qq号
     */
    public static String getRandomQQ() {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            val.append(random.nextInt(10));
        }
        return val.toString();
    }

}