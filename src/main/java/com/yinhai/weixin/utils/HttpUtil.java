package com.yinhai.weixin.utils;

import com.yinhai.weixin.exception.QueryException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 发送http请求
 * Created by 张汉江 on 2018/3/7
 */
public class HttpUtil {

    //传输超时时间，默认10秒
    private  static final int connectTimeout = 10000;

    //连接超时时间，默认6秒
    private  static final  int socketTimeout=6000;

    //根据默认超时限制初始化requestConfig
    private static RequestConfig requestConfig =
            RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();;

    /**
     * 发送post请求
     * @param url 请求地址
     * @param outputEntity 发送内容
     * @return  发送post请求以后  可以通过 response.getEntity().getContent() 拿到内容
     * @throws Exception
     */
    public static CloseableHttpResponse Post(String url, String outputEntity)throws  Exception{
        LogUtil.show(LogUtil.Level.INFO,"开始发送http的post请求，请求地址为："+url);
        HttpPost httpPost=new HttpPost(url);
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(new StringEntity(outputEntity, "UTF-8"));
        httpPost.setConfig(requestConfig);//设置超时时间
        return  HttpClients.custom().build().execute(httpPost);
    }

    /**
     * get 方法
     * @param strUrl
     * @return
     */
    public static String  get(String strUrl){
        LogUtil.show(LogUtil.Level.INFO,"开始发送http的get请求，请求地址为："+strUrl);
        StringBuilder json=new StringBuilder();
        String inputline=null;
        BufferedReader in=null;
        try {
            URL url=new URL(strUrl);
            URLConnection uc=url.openConnection();
            uc.setConnectTimeout(5000);//设置超时时间5秒
            uc.setReadTimeout(5000);
            in=new BufferedReader(new InputStreamReader(uc.getInputStream()));
            while ((inputline=in.readLine())!=null){
                json.append(inputline);
            }

        }catch (Exception e){
            LogUtil.show(LogUtil.Level.ERROR,"http连接发生错误",e);
            throw  new QueryException("http连接发生错误");
        }finally {

            try {
                if(in!=null){
                    in.close();
                }
            }catch (Exception m){
                LogUtil.show(LogUtil.Level.ERROR,"http关闭通道发生异常",m);
                throw  new QueryException("http关闭通道发生异常");
            }
        }
        return json.toString();
    }
}
