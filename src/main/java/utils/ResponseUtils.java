package utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Response工具类
 */
public class ResponseUtils {

    /**
     * 输出信息到页面
     */
    public static void writeObject(HttpServletResponse response, Object o){
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer=null;
        try {
            writer= response.getWriter();
            writer.println(o);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer!=null) {
                writer.flush();
                writer.close();
            }
        }
    }

}
