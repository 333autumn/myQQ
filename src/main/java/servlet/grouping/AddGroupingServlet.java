package servlet.grouping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.GroupingService;
import utils.ResponseUtils;
import utils.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/grouping/add")
public class AddGroupingServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(AddGroupingServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addGrouping(request, response);
    }

    /**
     * 新增好友分组
     */
    private void addGrouping(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取传入参数
        String name = request.getParameter("name");
        String userQQ = request.getParameter("userQQ");
        logger.info("name:{},userQQ:{}", name, userQQ);
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(userQQ)) {
            logger.info("传入参数为空");
            ResponseUtils.writeObject(response, "传入参数为空");
        }
        //新增好友分组
        if (GroupingService.addGrouping(name, userQQ)) {
            ResponseUtils.writeObject(response, "新增好友分组成功");
        } else {
            ResponseUtils.writeObject(response, "新增好友分组失败");
        }
    }

}
