package utils;

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

}