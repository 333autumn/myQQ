package mapper;

import com.alibaba.fastjson.JSON;
import eneity.ChatRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChatRecordMapper {

    private static final Logger logger= LoggerFactory.getLogger(ChatRecordMapper.class);

    /**
     * 查询两个用户间的聊天记录id
     */
    public static List<ChatRecord> selectRecords(String senderQQ, String receiverQQ){
        String sql1="select * from chat_record where senderQq='"+senderQQ+"'and receiverQq='"+receiverQQ+"'";
        Statement statement=dbUtils.getStatement();
        logger.info("查询聊天记录执行的sql为:{}",sql1);
        List<ChatRecord> list=new ArrayList<>();
        try {
            ResultSet resultSet1 = statement.executeQuery(sql1);
            List<ChatRecord> records1=jdbcUtils.ResultSetToBean(resultSet1,ChatRecord.class);
            logger.info("查询聊天记录结果为:{}", JSON.toJSONString(records1));
            list.addAll(records1);
        } catch (Exception e) {
            return null;
        }
        return list;
    }

    /**
     * 新增一条聊天记录
     */
    public static void addChatRecords(ChatRecord chatRecord){
        String sql="insert into chat_record values ("+chatRecord.getSql()+")";
        logger.info("新增聊天记录执行的sql为:"+sql);
        Statement statement=dbUtils.getStatement();
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            logger.info("新增聊天记录发生错误");
        }
    }
}
