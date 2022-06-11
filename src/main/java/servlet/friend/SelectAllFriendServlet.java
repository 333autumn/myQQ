package servlet.friend;

import com.alibaba.fastjson.JSON;
import eneity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.FriendService;
import utils.ResponseResult;
import utils.ResponseUtils;
import utils.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/friend/selectAllFriend")
public class SelectAllFriendServlet extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(SelectAllFriendServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        selectAllFriend(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * 获取给定用户的所有好友
     */
    //http://localhost:8888/myQQ/friend/selectAllFriend?selfQQ=111
    private void selectAllFriend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String selfQQ = request.getParameter("selfQQ");
        if (StringUtils.isEmpty(selfQQ)) {
            ResponseUtils.writeObject(response, ResponseResult.error("传入参数为空"));
        }
        logger.info("selfQQ:{}", selfQQ);
        List<User> friends = FriendService.selectAllFriend(selfQQ);
        ResponseUtils.writeObject(response, ResponseResult.ok(friends));
    }
}
