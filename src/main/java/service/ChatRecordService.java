package service;

import eneity.ChatRecord;
import dao.ChatRecordDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatRecordService {
    /**
     * 搜索两个用户间的所有聊天记录
     */
    public static List<ChatRecord> selectRecords(String senderQQ, String receiverQQ){
        List<ChatRecord> records1 = ChatRecordDao.selectRecords(senderQQ, receiverQQ);
        List<ChatRecord> records2 = ChatRecordDao.selectRecords(receiverQQ, senderQQ);
        List<ChatRecord> list=new ArrayList<>();
        if (Objects.nonNull(records1)){
            list.addAll(records1);
        }
        if (Objects.nonNull(records2)){
            list.addAll(records2);
        }
        return list;
    }
    /**
     * 新增聊天记录
     */
    public static void addChatRecords(ChatRecord chatRecord){
        ChatRecordDao.addChatRecords(chatRecord);
    }

}
