package servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.FriendService;
import utils.ResponseUtils;
import utils.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/friend/addFriend")
public class AddFriendServlet extends HttpServlet {
    private static final Logger logger= LoggerFactory.getLogger(AddFriendServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addFriend(request, response);
    }

    /**
     * 添加好友
     */
    private void addFriend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取传入参数
        String selfQQ=request.getParameter("selfQQ");
        String friendQQ=request.getParameter("friendQQ");
        logger.info("本人qq:{},好友qq:{}",selfQQ,friendQQ);
        if (StringUtils.isEmpty(selfQQ)||StringUtils.isEmpty(friendQQ)){
            logger.info("新增好友传入参数为空");
            ResponseUtils.writeObject(response,"传入参数为空");
        }
        if (FriendService.addFriend(selfQQ,friendQQ)){
            logger.info("新增好友成功");
            ResponseUtils.writeObject(response,"新增好友成功");
        }else {
            logger.info("新增好友失败");
            ResponseUtils.writeObject(response,"新增好友失败");
        }
    }
}
