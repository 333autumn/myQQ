package service;

import eneity.User;
import mapper.UserMapper;

public class UserService {
    /**
     * 查询用户是否存在
     */
    public static boolean isAlive(User user){
        User select= UserMapper.selectByQQ(user.getQq());
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
        User user=new User();
        user.setQq(qq);
        //用户不存在,无法登录
        if (!isAlive(user)){
            return false;
        }
        User select=UserMapper.selectByQQ(qq);
        if (select==null){
            return false;
        }
        return select.getPassword().equals(password);
    }

}
