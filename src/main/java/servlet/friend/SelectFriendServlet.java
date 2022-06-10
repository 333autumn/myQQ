package servlet.friend;


import com.alibaba.fastjson.JSON;
import eneity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.FriendService;
import utils.ResponseUtils;
import utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/friend/selectByQQ")
public class SelectFriendServlet extends HttpServlet {

    private static final Logger logger= LoggerFactory.getLogger(SelectFriendServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        selectUserByQQ(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     * 通过qq号查询用户信息(模糊查询)
     */
    //http://localhost:8888/myQQ/friend/selectByQQ?qq=111
    private void selectUserByQQ(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        //获取传入参数
        String qq=request.getParameter("qq");
        logger.info("qq:{}",qq);
        if (StringUtils.isEmpty(qq)){
            ResponseUtils.writeObject(response,"传入的qq为空");
        }
        //通过qq模糊查询用户信息
        List<User> users = FriendService.selectFriendsByQQ(qq);
        //返回信息
        ResponseUtils.writeObject(response, JSON.toJSONString(users));
    }
}