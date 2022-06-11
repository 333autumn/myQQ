package ws;

import com.alibaba.fastjson.JSON;
import eneity.ChatRecord;
import eneity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ChatRecordService;
import service.UserService;
import utils.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chat/{qq}/{password}")
//ws://localhost:8888/myQQ/chat/111/123
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
    public void onOpen(Session session, @PathParam("qq") String qq,@PathParam("password") String password){
        logger.info("登录qq:{},登录密码:{}",qq,password);
        if (StringUtils.isEmpty(qq)||StringUtils.isEmpty(password)){
            logger.info("参数为空,登录失败");
            return;
        }
        //判断密码是否正确
       if (!UserService.login(qq, password)){
           logger.info("密码错误,登录失败");
           return;
       }
        logger.info("密码正确,登录成功");
       //密码正确,成功登录,保存当前会话
        this.qq=qq;
        //保存当前会话
        this.session=session;
        //将qq和当前对象加入到容器中
        users.put(this.qq,this);
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
        //获取传入参数
        Message m= JSON.parseObject(message,Message.class);
        String receiverQq=m.getReceiverQq();
        logger.info("receiverQq:{}",receiverQq);
        String info=m.getMessage();
        logger.info("要发送的消息:{}",info);
        //判断接收人是否在线
        if (users.get(receiverQq)!=null){
            try {
                users.get(receiverQq).session.getBasicRemote().sendText(info);
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

