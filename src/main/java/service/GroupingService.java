package service;

import eneity.Grouping;
import mapper.GroupingMapper;

import java.time.LocalDate;

public class GroupingService {


    /**
     * 新增好友分组
     */
    public static boolean addGrouping(String name, String userQQ) {
        //封装分组对象
        Grouping grouping=new Grouping();
        grouping.setCreatedDate(LocalDate.now());
        grouping.setName(name);
        grouping.setUserQQ(userQQ);
        return GroupingMapper.addGrouping(grouping);
    }
}
