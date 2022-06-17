package mapper;

import com.alibaba.fastjson.JSON;
import eneity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.dbUtils;
import utils.jdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserMapper {

    private static final Logger logger= LoggerFactory.getLogger(UserMapper.class);

    /**
     * 根据qq查询用户信息
     */
    public static User selectByQQ(String qq) {
        String sql = "select * from user where qq=" + qq;
        Connection connection=dbUtils.getConnection();
        Statement statement= null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("查询用户信息执行的sql为:{}",sql);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> users = jdbcUtils.ResultSetToBean(resultSet, User.class);
            if (users.size()==1){
                return users.get(0);
            }
        } catch (Exception e) {
            return null;
        }finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 新增用户
     */
    public static boolean addUser(User user) {
        String sql="insert into user set "+user.sql();
        Connection connection=dbUtils.getConnection();
        Statement statement= null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("新增用户执行的sql为:{}",sql);
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    /**
     * 根据qq号查询用户信息
     */
    public static User getUserInfo(String qq) {
        String sql="select * from user where qq ='"+qq+"'";
        Connection connection=dbUtils.getConnection();
        Statement statement= null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("查询用户信息执行的sql为:{}", sql);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> users = jdbcUtils.ResultSetToBean(resultSet, User.class);
            logger.info("查询用户结果:{}", JSON.toJSON(users).toString());
            return users.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 根据qq模糊查询用户信息
     */
    public static List<User> selectByQQLike(String qq) {
        String sql = "select * from user where qq like '%" + qq + "%'";
        Connection connection=dbUtils.getConnection();
        Statement statement= null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("根据qq模糊查询用户信息执行的sql为:{}", sql);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> users = jdbcUtils.ResultSetToBean(resultSet, User.class);
            logger.info("根据qq模糊查询用户结果:{}", JSON.toJSON(users).toString());
            return users;
        } catch (Exception e) {
            return null;
        }finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据username模糊查询用户信息
     */

    public static List<User> selectByUserNameLike(String username) {
        String sql = "select * from user where username like '%" + username + "%'";
        Connection connection=dbUtils.getConnection();
        Statement statement= null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("根据username模糊查询用户信息执行的sql为:{}", sql);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> users = jdbcUtils.ResultSetToBean(resultSet, User.class);
            logger.info("根据username模糊查询用户结果:{}", JSON.toJSON(users).toString());
            return users;
        } catch (Exception e) {
            return null;
        }finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
