package servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;

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

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取传入数据
        String qq=request.getParameter("qq");
        String password=request.getParameter("password");
        logger.info("qq:{},password:{}",qq,password);
        //判断密码是否正确
        boolean flag = UserService.login(qq, password);
        response.setContentType("text/json;charset=utf-8");
        if (flag){
            response.getWriter().write("用户登录成功");
        }else {
            response.getWriter().write("用户登录失败");
        }
    }
}
