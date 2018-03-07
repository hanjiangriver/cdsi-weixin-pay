package com.yinhai.weixin.utils;

import com.alibaba.fastjson.JSONObject;
import com.yinhai.weixin.exception.QueryException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

/**
 * 发送https请求
 * Created by 张汉江 on 2018/3/7
 */
public class HttpsUtil {

    /**
     * 发起https 请求并获取数据
     * @param requestUrl 请求地址
     * @param requestMethod 请求方法 get post
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr){
        JSONObject jsonObject=null;
        StringBuffer buffer=new StringBuffer();

        try {
            //创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm={new MyX509TrustManager()};
            SSLContext sslContext=SSLContext.getInstance("SSL","SunJSSE");
            sslContext.init(null,tm,new java.security.SecureRandom());

            //从上述SSLContext 对象中得到SSLSocketFactory 对象
            SSLSocketFactory ssf=sslContext.getSocketFactory();
            URL url=new URL(requestUrl);
            HttpsURLConnection httpUrlConn=(HttpsURLConnection)url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);
           // httpUrlConn.setRequestProperty();
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setConnectTimeout(6000);//设置超时时间

            //设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if("GET".equalsIgnoreCase(requestMethod)){
                httpUrlConn.connect();
            }
            //当有数据需要提交时
            if(null!=outputStr){
                //防止乱码
                OutputStream outputStream=httpUrlConn.getOutputStream();
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            //将返回的输入流转换成字符串
            InputStream inputStream=httpUrlConn.getInputStream();
            //将字节流转换成字符流
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

            String str=null;
            while((str=bufferedReader.readLine())!=null){
                buffer.append(str);
            }
            bufferedReader.close();
            inputStream.close();
            httpUrlConn.disconnect();
            jsonObject=JSONObject.parseObject(buffer.toString());//???这里有问题  这个只有是json串时才能使用
        }catch (ConnectException ce){
            LogUtil.show(LogUtil.Level.ERROR,"Weixin server connection timed out.",ce);
            throw  new QueryException("Weixin server connection timed out.");
        }catch (Exception e){
            LogUtil.show(LogUtil.Level.ERROR,"https request error.",e);
            throw  new QueryException("https request error.");

        }

        return jsonObject;
    }
}
