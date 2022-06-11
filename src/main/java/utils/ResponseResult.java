package utils;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class ResponseResult implements Serializable {
    private Integer code;
    private String msg;
    private Object data;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 请求成功
     */
    public static String ok(Object data){
        ResponseResult result=new ResponseResult();
        result.setCode(200);
        result.setMsg("请求成功");
        result.setData(data);
        return JSON.toJSONString(result);
    }

    public static String ok(String msg,Object data){
        ResponseResult result=new ResponseResult();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(data);
        return JSON.toJSONString(result);
    }

    public static String ok(String msg){
        ResponseResult result=new ResponseResult();
        result.setCode(200);
        result.setMsg(msg);
        result.setData("{}");
        return JSON.toJSONString(result);
    }


    /**
     * 请求失败
     */
    public static String error(){
        ResponseResult result=new ResponseResult();
        result.setCode(500);
        result.setMsg("请求失败");
        result.setData("{}");
        return JSON.toJSONString(result);
    }

    public static String error(String msg){
        ResponseResult result=new ResponseResult();
        result.setCode(500);
        result.setMsg(msg);
        result.setData("{}");
        return JSON.toJSONString(result);
    }

}