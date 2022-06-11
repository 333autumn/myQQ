package servlet.user;

import eneity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import utils.ResponseResult;
import utils.ResponseUtils;
import utils.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/info")
public class UserInfoServlet extends HttpServlet {
    private static final Logger logger= LoggerFactory.getLogger(UserInfoServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getUserInfo(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getUserInfo(request, response);
    }
    /**
     * 通过qq号获取用户信息
     */
    //http://localhost:8888/myQQ/user/info?qq=111
    private void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取传入参数
        String qq=request.getParameter("qq");
        if (StringUtils.isEmpty(qq)){
            ResponseUtils.writeObject(response, ResponseResult.error("传入参数为空"));
        }
        logger.info("qq:{}",qq);
        User userInfo = UserService.getUserInfo(qq);
        //服务端响应
        ResponseUtils.writeObject(response,ResponseResult.ok("获取用户信息成功",userInfo));
    }
}
