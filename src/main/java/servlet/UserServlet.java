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

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private static final Logger logger= LoggerFactory.getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type=request.getParameter("type");
        logger.info("type={}",type);
        if (type.equals("register")){
            register(request,response);
        }else if (type.equals("login")){
            login(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

    /**
     * 用户登录
     */
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
