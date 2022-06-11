package servlet.user;

import com.alibaba.fastjson.JSON;
import eneity.User;
import mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import utils.ResponseResult;
import utils.ResponseUtils;
import utils.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/user/register")
public class UserRegisterServlet extends HttpServlet {
    private static final Logger logger= LoggerFactory.getLogger(UserRegisterServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        register(request,response);
    }

    /**
     * 用户注册
     */
    //http://localhost:8888/myQQ/user/register?username=111&password=1234
    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取传入参数
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        logger.info("username:{},password:{}",username,password);
        if (StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            ResponseUtils.writeObject(response, ResponseResult.error("传入参数为空"));
        }
        String qq=UserService.register(username,password);
        if (qq!=null) {//注册成功
            User user=UserService.getUserInfo(qq);
            ResponseUtils.writeObject(response,ResponseResult.ok("注册成功",user));
        }else {//注册失败
            ResponseUtils.writeObject(response, ResponseResult.error("注册失败"));
        }
    }
}
