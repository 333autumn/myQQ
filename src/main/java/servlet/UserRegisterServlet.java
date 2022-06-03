package servlet;

import com.alibaba.fastjson.JSON;
import eneity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;

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
    //http://localhost:8888/myQQ/user?type=register
    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过流获取传入参数
        BufferedReader br = request.getReader();
        String params = br.readLine();
        logger.info("用户注册传入参数:{}", params);
        //调用fastjson的方法将json字符串封装为java对象
        User user = JSON.parseObject(params, User.class);
        //用户存在返回错误信息
        if (UserService.isAlive(user)) {
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write("用户已存在");
        }
        //不存在调用方法进行新增
        boolean temp = UserService.addUser(user);
        response.setContentType("text/json;charset=utf-8");
        if (temp) {
            response.getWriter().write("用户注册成功");
        } else {
            response.getWriter().write("用户注册失败");
        }
    }
}
