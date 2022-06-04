package service;

import eneity.User;
import mapper.FriendMapper;
import mapper.UserMapper;

import java.util.List;
import java.util.Objects;

public class FriendService {

    /**
     * 通过qq号模糊查询用户信息
     */
    public static List<User> selectFriendsByQQ(String qq){
        List<User> users = UserMapper.selectByQQLike(qq);
        return users;
    }

    /**
     * 添加好友
     */
    public static boolean addFriend(String selfQQ,String friendQQ){
        //判断两个用户是否存在
        User self=new User();
        self.setQq(selfQQ);
        User friend=new User();
        friend.setQq(friendQQ);
        if (!UserService.isAlive(self)||!UserService.isAlive(friend)){
            return false;
        }
        return FriendMapper.addFriend(selfQQ,friendQQ);
    }

    /**
     * 获取指定用户的所有好友
     */
    public static List<User> selectAllFriend(String selfQQ) {
        List<User> friends=FriendMapper.selectFriendsInfo(selfQQ);
        return friends;
    }
}
