package service;

import eneity.User;
import mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.StringUtils;

import java.time.LocalDateTime;


public class UserService {
    private static final Logger logger= LoggerFactory.getLogger(UserService.class);
    /**
     * 查询用户是否存在
     */
    public static boolean isAlive(String qq){
        User select= UserMapper.selectByQQ(qq);
        return select != null;
    }

    /**
     * 新增用户
     */
    public static boolean addUser(User user){
        return UserMapper.addUser(user);
    }

    /**
     * 用户登录
     */
    public static boolean login(String qq,String password){
        //通过qq号查找到对应的用户
        //用户不存在,无法登录
        if (!isAlive(qq)){
            return false;
        }
        User select=UserMapper.selectByQQ(qq);
        if (select==null){
            return false;
        }
        return select.getPassword().equals(password);
    }

    /**
     * 根据qq号查询用户信息
     */
    public static User getUserInfo(String qq){
        return UserMapper.getUserInfo(qq);
    }

    /**
     * 用户注册
     */
    public static String register(String username, String password) {
        //随机生成qq直到qq号不重复
        String qq= StringUtils.getRandomQQ();
        while (isAlive(qq)){
            qq= StringUtils.getRandomQQ();
        }
        logger.info("随机生成的不重复的qq为:{}",qq);
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setQq(qq);
        user.setCreateDate(LocalDateTime.now());
        if (UserMapper.addUser(user)){
            return qq;
        }
        return null;
    }

}
