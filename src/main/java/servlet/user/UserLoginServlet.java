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

@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
    private static final Logger logger= LoggerFactory.getLogger(UserLoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        login(request, response);
    }

    //http://localhost:8888/myQQ/user/login?qq=111&password=123
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取传入数据
        String qq = request.getParameter("qq");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(qq) || StringUtils.isEmpty(password)) {
            ResponseUtils.writeObject(response, ResponseResult.error("传入参数为空"));
        }
        logger.info("qq:{},password:{}", qq, password);
        //判断密码是否正确
        boolean flag = UserService.login(qq, password);
        if (flag) {
            //获取用户信息
            User user=UserService.getUserInfo(qq);
            ResponseUtils.writeObject(response, ResponseResult.ok("用户登录成功",user));
        } else {
            ResponseUtils.writeObject(response, ResponseResult.error("用户登录失败"));
        }
    }
}
