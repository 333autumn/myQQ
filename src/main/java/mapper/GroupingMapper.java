package mapper;

import eneity.Grouping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Statement;
import utils.dbUtils;

public class GroupingMapper {

    private static final Logger logger= LoggerFactory.getLogger(GroupingMapper.class);

    /**
     * 新增好友分组
     */
    public static boolean addGrouping(Grouping grouping) {
        String sql="insert into `grouping` values ("+grouping.sql()+")";
        logger.info("新增好友分组执行的sql为:{}",sql);
        Statement statement=dbUtils.getStatement();
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            logger.error("新增好友分组失败");
            return false;
        }
        logger.info("新增好友分组成功");
        return true;
    }

    /**
     * 更改好友分组
     */


}
