import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class test {
    public static void main(String[] args) {
        String temp="{'name':'tom'}";
        //将json转化为map
        JSONObject map=JSON.parseObject(temp);
        System.out.println(map.get("name"));
    }
}
