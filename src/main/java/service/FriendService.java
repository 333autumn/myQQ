package service;

import eneity.User;
import dao.FriendDao;
import dao.UserDao;

import java.util.ArrayList;
import java.util.List;

public class FriendService {

    /**
     * 通过qq号模糊查询用户信息
     */
    public static List<User> selectFriendsByContent(String content){
        List<User> users1 = UserDao.selectByQQLike(content);
        List<User> users2= UserDao.selectByUserNameLike(content);
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
        if (FriendDao.isFriend(selfQQ,friendQQ)&& FriendDao.isFriend(friendQQ,selfQQ)){
            return false;
        }
        return FriendDao.addFriend(selfQQ,friendQQ)&& FriendDao.addFriend(friendQQ,selfQQ);
    }

    /**
     * 获取指定用户的所有好友
     */
    public static List<User> selectAllFriend(String selfQQ) {
        List<User> friends= FriendDao.selectFriendsInfo(selfQQ);
        return friends;
    }
}
