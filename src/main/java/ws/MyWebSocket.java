package ws;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import eneity.ChatRecord;
import eneity.Message;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ChatRecordService;
import utils.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chat/{qq}")
//ws://localhost:8080/myQQ/chat/111
public class MyWebSocket {

    private static final Logger logger= LoggerFactory.getLogger(MyWebSocket.class);

    //ConcurrentHashMap是线程安全的哈希表
    //键为qq号
    //值为当前websocket会话
    private static final Map<String, MyWebSocket> users =new ConcurrentHashMap<>();
    //用于保存当前会话
    private Session session;

    private String qq;

    /**
     * 建立连接时调用
     * 相当于用户登录
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("qq") String qq) {
        logger.info("登录qq:{}", qq);
        if (StringUtils.isEmpty(qq)) {
            logger.info("参数为空,登录失败");
        }
        if (users.containsKey(qq)){
            logger.info("用户已登录");
        }
        //成功登录,保存当前qq
        this.qq = qq;
        //保存当前会话
        this.session = session;
        //将qq和当前对象加入到容器中
        users.put(this.qq, this);
    }

    /**
     * 发送消息时调用
     */
    @OnMessage
    public void onMessage(String message){
        if (StringUtils.isEmpty(message)){
            logger.info("发送消息传入参数为空,发送消息失败");
            return;
        }
        logger.info("当前登录qq==>{}",qq);
        //获取传入参数
        //将json字符串解析为java对象
        message= StringEscapeUtils.unescapeJava(message);
        Message m= JSON.parseObject(message,Message.class);
        String receiverQq=m.getReceiverQq();
        logger.info("receiverQq:{}",receiverQq);
        String info=m.getMessage();
        logger.info("要发送的消息:{}",info);
        //判断接收人是否在线
        if (users.get(receiverQq)!=null){
            try {
                users.get(receiverQq).session.getBasicRemote().sendText(qq+","+info);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("发生错误,信息发送失败");
            }
        }
        //封装聊天记录对象
        ChatRecord chatRecord=new ChatRecord();
        chatRecord.setChatContent(info);
        chatRecord.setSenderQq(qq);
        chatRecord.setReceiverQq(receiverQq);
        chatRecord.setCreatedDate(LocalDateTime.now());
        //保存聊天记录
        ChatRecordService.addChatRecords(chatRecord);
        logger.info("保存信息成功,信息内容:{}",info);
        logger.info("qq==>{}",qq);
//        //群发消息
//        List<MyWebSocket> list= new ArrayList<>(users.values());
//        for (MyWebSocket socket : list) {
//            try {
//                socket.session.getBasicRemote().sendText(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @OnClose
    public void onClose(){
        logger.info("退出登录,qq:{}",this.qq);
        //将当前对象从容器中移除
        users.remove(this.qq);
    }

    @OnError
    public void onError(Throwable t){
        logger.error("websocket发生错误");
        t.printStackTrace();
    }

}
