package servlet;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/friend/selectByQQ")
public class FriendSelectServlet extends HttpServlet {

    private static final Logger logger= LoggerFactory.getLogger(FriendSelectServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        selectUserByQQ(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     * 通过qq号查询用户(模糊查询)
     */
    private void selectUserByQQ(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        //获取传入参数
        String qq=request.getParameter("qq");
        logger.info("qq:{}",qq);
        //通过qq模糊查询用户信息
    }
}