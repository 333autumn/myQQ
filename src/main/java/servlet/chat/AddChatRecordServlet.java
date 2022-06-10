package servlet.chat;

import com.alibaba.fastjson.JSON;
import eneity.ChatRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ChatRecordService;
import utils.ResponseUtils;
import utils.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/chat/addChatRecords")
public class AddChatRecordServlet extends HttpServlet {
    private static final Logger logger= LoggerFactory.getLogger(AddChatRecordServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addChatRecords(request, response);
    }

    /**
     * 添加聊天记录
     */
    private void addChatRecords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取传入的参数
        BufferedReader br = request.getReader();
        String params = br.readLine();
        logger.info("新增聊天记录传入参数:{}", params);
        if (StringUtils.isEmpty(params)) {
            ResponseUtils.writeObject(response, "传入的参数为空");
        }
        //调用fastjson的方法将json字符串封装为java对象
        ChatRecord chatRecord = JSON.parseObject(params, ChatRecord.class);
        //调用方法新增聊天记录
        ChatRecordService.addChatRecords(chatRecord);
        ResponseUtils.writeObject(response, "新增聊天记录成功");
    }
}
