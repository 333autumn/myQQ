package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库连接工具类
 */
public class dbUtils {

    /**
     * 定义连接参数
     */
    //mysql连接地址
    private static final String url = "jdbc:mysql://localhost:3306/qq?useSSL=false&serverTimezone=UTC";
    //mysql用户名
    private static final String user = "root";
    //mysql密码
    private static final String password = "root";

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() {
        //注册数据库驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("注册数据库驱动失败");
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取数据库连接失败");
        }
        return connection;
    }


}
