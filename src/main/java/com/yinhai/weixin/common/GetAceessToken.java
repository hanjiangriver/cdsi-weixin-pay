package com.yinhai.weixin.common;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.yinhai.weixin.configuration.AppConfig;
import com.yinhai.weixin.exception.QueryException;
import com.yinhai.weixin.model.AccessToken;
import com.yinhai.weixin.utils.HttpsUtil;
import com.yinhai.weixin.utils.LogUtil;

/**
 * 获得通用接口的token
 * Created by 张汉江 on 2018/3/8
 */
public class GetAceessToken {
    /**
     * 获取token
     * @param appid
     * @param appsecret
     * @return  "errmsg" -> "invalid ip 117.136.62.27, not in whitelist hint: [bQSqwA0850sha8]"
     */
    public static AccessToken getAccessToken(String appid, String appsecret){
        AccessToken accessToken=null;
        String requestUrl= AppConfig.access_token_url.replace("APPID",appid).replace("APPSECRET",appsecret);
        JSONObject jsonObject= HttpsUtil.httpRequest(requestUrl,"GET",null);

        //如果请求成功
        if(null!=jsonObject){
            try {
                accessToken=new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getIntValue("expires_in"));
            }catch (JSONException e){
                accessToken=null;
                //获取token失败
                LogUtil.show(LogUtil.Level.ERROR,"获取token失败"+jsonObject.getString("errmsg"),e);
                throw new QueryException("获取token失败");
            }
        }
        return  accessToken;
    }
}
