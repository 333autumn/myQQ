package mapper;

import com.alibaba.fastjson.JSON;
import eneity.Friend;
import eneity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.dbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import utils.jdbcUtils;
import java.util.List;

public class FriendMapper {

    private static final Logger logger= LoggerFactory.getLogger(FriendMapper.class);

    /**
     * 添加好友
     */
    public static boolean addFriend(String selfQQ, String friendQQ) {
        //得到现在的时间
        LocalDate now=LocalDate.now();
        Friend friend=new Friend();
        friend.setCreatedDate(now);
        friend.setSelfQQ(selfQQ);
        friend.setFriendQQ(friendQQ);
        String sql="insert into friend set "+friend.sql();
        Statement statement= dbUtils.getStatement();
        logger.info("添加好友执行的sql为:{}",sql);
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 通过用户的qq获取所有好友的用户信息
     */
    public static List<User> selectFriendsInfo(String selfQQ) {
        String sql = "select * from user where qq in ( select friendQQ from friend  where selfQQ=" + selfQQ + ")";
        Statement statement = dbUtils.getStatement();
        List<User> friends;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            friends = jdbcUtils.ResultSetToBean(resultSet, User.class);
            logger.info("查询好友结果为:{}", JSON.toJSONString(friends));
        } catch (Exception e) {
            return null;
        }
        return friends;
    }
}
