package com.yinhai.weixin.utils;

import com.yinhai.weixin.configuration.AppConfig;
import com.yinhai.weixin.exception.AppException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 服务器端 验签 （确认是微信服务器发送过来的 然后开启自定义消息）
 * Created by 张汉江 on 2018/3/8
 */
public class ServerSignUtil {

    public static  boolean checkSignature(String signature, String timestamp,
                                          String nonce){
        LogUtil.show(LogUtil.Level.INFO,"开始执行验签:"+"signature:"+signature+"timestamp:"+timestamp+"nonce:"+nonce);
        String[] arr=new String[]{AppConfig.token,timestamp,nonce};
        //将token timestamp nonce进行排序
        Arrays.sort(arr);
        StringBuilder content=new StringBuilder();
        for(int i=0;i<arr.length;i++){
            content.append(arr[i]);
        }
        MessageDigest md=null;
        String tempStr=null;

        try {
            md=MessageDigest.getInstance("SHA-1");
            //将这三个字符串 转换成一个字符串  并进行加密
            byte[] digest= md.digest(content.toString().getBytes());
            tempStr=byteToStr(digest);

        } catch (NoSuchAlgorithmException e) {
            LogUtil.show(LogUtil.Level.ERROR,"验签发生错误",e);
            throw new AppException("验签发生错误");
        }
        content=null;
        return tempStr!=null?tempStr.equalsIgnoreCase(signature.toUpperCase()):false;
    }


    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }
}
