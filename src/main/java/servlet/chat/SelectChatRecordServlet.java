package servlet.chat;

import com.alibaba.fastjson.JSON;
import eneity.ChatRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ChatRecordService;
import utils.ResponseResult;
import utils.ResponseUtils;
import utils.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/chat/selectAllRecords")
public class SelectChatRecordServlet extends HttpServlet {

    private static final Logger logger= LoggerFactory.getLogger(SelectChatRecordServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        selectAllChatRecords(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * 获取自己和对方的所有聊天记录
     */
    //http://localhost:8888/myQQ//chat/selectAllRecords?senderQQ=222&receiverQQ=111
    private void selectAllChatRecords(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        //获取传入参数
        String senderQQ = request.getParameter("senderQQ");
        String receiverQQ = request.getParameter("receiverQQ");
        if (StringUtils.isEmpty(senderQQ) || StringUtils.isEmpty(receiverQQ)) {
            logger.info("查看聊天记录传入参数为空");
            ResponseUtils.writeObject(response, ResponseResult.error("传入参数为空"));
        }
        logger.info("发送人qq:{},接收人qq:{}", senderQQ, receiverQQ);
        List<ChatRecord> records = ChatRecordService.selectRecords(senderQQ, receiverQQ);
        ResponseUtils.writeObject(response, ResponseResult.ok(records));
    }

}
