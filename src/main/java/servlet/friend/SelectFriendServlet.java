package servlet.friend;


import com.alibaba.fastjson.JSON;
import eneity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.FriendService;
import utils.ResponseResult;
import utils.ResponseUtils;
import utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/friend/selectByContent")
public class SelectFriendServlet extends HttpServlet {

    private static final Logger logger= LoggerFactory.getLogger(SelectFriendServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        selectUserByContent(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     * 通过qq或者username查询用户信息(模糊查询)
     */
    //http://localhost:8888/myQQ/friend/selectByContent?content=111
    private void selectUserByContent(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        //获取传入参数
        String content=request.getParameter("content");
        if (StringUtils.isEmpty(content)){
            ResponseUtils.writeObject(response, ResponseResult.error("传入参数为空"));
        }
        logger.info("content:{}",content);
        //通过qq或者username模糊查询用户信息
        List<User> users = FriendService.selectFriendsByContent(content);
        //返回信息
        ResponseUtils.writeObject(response, JSON.toJSONString(users));
    }
}