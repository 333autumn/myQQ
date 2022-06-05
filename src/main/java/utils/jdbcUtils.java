package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * jdbc工具类
 */
public class jdbcUtils {
    /**
     * MySQL数据库将result set封装为Bean对象，需要数据库字段名和bean对象属性名一致(不区分大小写)
     */
    public static <T> List<T> ResultSetToBean(ResultSet resultSet, Class<T> beanClass) throws Exception {
        // 获取Bean对象内的所有属性
        Field[] fields = beanClass.getDeclaredFields();
        List<T> beanList = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) {
                // 每当有一行数据就创建一个Bean对象
                T object = beanClass.newInstance();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    // 利用字符串拼接，将属性名的首字母变为大写，获取对应的set方法。
                    Method setField = beanClass.getMethod
                            ("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), field.getType());
                    // 利用MySQL数据库不区分大小写的性质获取对应字段的值
//                    System.out.println(setField.getName()+"-----"+ resultSet.getObject(fieldName.toUpperCase()));
                    setField.invoke(object, resultSet.getObject(fieldName.toUpperCase()));
                }
                beanList.add(object);
            }
        }
        return beanList;
    }

}
