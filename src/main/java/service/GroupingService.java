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

    /**
     * 更改好友分组
     * @param selfQQ 本人qq
     * @param friendQQ 好友qq
     * @param groupingId 分组id
     */
    public static boolean changeGrouping(String selfQQ, String friendQQ, int groupingId) {
        return GroupingMapper.changeGrouping(selfQQ,friendQQ,groupingId);
    }
}
