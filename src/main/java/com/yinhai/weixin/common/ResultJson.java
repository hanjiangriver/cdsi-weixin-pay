package com.yinhai.weixin.common;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.Set;

/** code  0 代表成功  1 代表失败
 * 调用接口  返回结果（json 格式 然后在前端拿到以后可以 转成json对象）
 * Created by 张汉江 on 2018/3/8
 */
public class ResultJson {

    public static  String getJSONString(int  code){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",code);
        return  jsonObject.toJSONString();
    }

    public static String getJSONString(int code ,String message){
       JSONObject jsonObject=new JSONObject();
       jsonObject.put("code",code);
       jsonObject.put("msg",message);
       return  jsonObject.toJSONString();
    }


    public static String getJSONString(int code, Map<String,Object> map){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",code);
        for(Map.Entry<String,Object> entry:map.entrySet()){
            jsonObject.put(entry.getKey(),entry.getValue());
        }
        return jsonObject.toJSONString();
    }
}
