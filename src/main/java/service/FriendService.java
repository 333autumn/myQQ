package service;

import eneity.User;
import mapper.FriendMapper;
import mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

public class FriendService {

    /**
     * 通过qq号模糊查询用户信息
     */
    public static List<User> selectFriendsByContent(String content){
        List<User> users1 = UserMapper.selectByQQLike(content);
        List<User> users2= UserMapper.selectByUserNameLike(content);
        List<User> list=new ArrayList<>();
        list.addAll(users1);
        list.addAll(users2);
        return list;
    }

    /**
     * 添加好友
     */
    public static boolean addFriend(String selfQQ,String friendQQ){
        //判断两个用户是否存在
        if (!UserService.isAlive(selfQQ)||!UserService.isAlive(friendQQ)){
            return false;
        }
        //判断两个用户是否已经为好友
        if (FriendMapper.isFriend(selfQQ,friendQQ)&&FriendMapper.isFriend(friendQQ,selfQQ)){
            return false;
        }
        return FriendMapper.addFriend(selfQQ,friendQQ)&&FriendMapper.addFriend(friendQQ,selfQQ);
    }

    /**
     * 获取指定用户的所有好友
     */
    public static List<User> selectAllFriend(String selfQQ) {
        List<User> friends=FriendMapper.selectFriendsInfo(selfQQ);
        return friends;
    }
}
