package servlet.grouping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.GroupingService;
import utils.ResponseUtils;
import utils.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/grouping/change")
public class ChangeGroupingServlet extends HttpServlet {
    private static final Logger logger= LoggerFactory.getLogger(ChangeGroupingServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        changeGrouping(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        changeGrouping(request, response);
    }

    /**
     * 更改好友分组
     */
    private void changeGrouping(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        BufferedReader br = request.getReader();
        String json = br.readLine();
        logger.info("更改好友分组传入参数:{}", json);
        if (StringUtils.isEmpty(json)) {
            ResponseUtils.writeObject(response, "传入的参数为空");
        }
        //将json转化为map
        JSONObject map = JSON.parseObject(json);
        String selfQQ = map.getString("selfQQ");
        String friendQQ=map.getString("friendQQ");
        int groupingId=map.getInteger("groupingId");
        //更改好友分组
        if (GroupingService.changeGrouping(selfQQ,friendQQ,groupingId)){//更改好友分组成功
            ResponseUtils.writeObject(response,"更改好友分组成功");
        }else {
            ResponseUtils.writeObject(response,"更改好友分组失败");
        }
    }


}
