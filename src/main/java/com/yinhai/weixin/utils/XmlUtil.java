package com.yinhai.weixin.utils;

import com.yinhai.weixin.exception.AppException;
import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.*;

/**
 * 解析xml
 * Created by 张汉江 on 2018/3/7
 */
public class XmlUtil {

    /**
     * 把输入流转换成xml
     * @param inputStream
     * @return
     * @throws DocumentException
     */
    public static String inputToXml(InputStream inputStream) throws DocumentException {
        return  inputToDocument(inputStream).asXML();
    }

    private static Document inputToDocument(InputStream inputStream) throws DocumentException {
        SAXReader saxReader=new SAXReader();
        return saxReader.read(inputStream);
    }


    /**
     * 解析微信服务器发来的请求
     * @param inputStream
     * @return 微信返回的参数集合
     */
    public static Map<String,String> parseXmlToMap(InputStream inputStream){
        SortedMap<String,String> map=new TreeMap<>();
        try {
            //获取request 输入流
            Document document=inputToDocument(inputStream);
            //得到xml根元素
            Element root=document.getRootElement();
            //得到根元素的所有节点
            List<Element> elementList=root.elements();
            //遍历所有节点
            for(Element element :elementList){
                map.put(element.getName(),element.getText());
            }
        }catch (Exception e){
            LogUtil.show(LogUtil.Level.ERROR,"微信工具类:解析xml异常",e);
            throw  new AppException("微信工具类:解析xml异常");
        }finally {
            try {
                if(inputStream!=null){
                    inputStream.close();
                }
            }catch (Exception m){
                LogUtil.show(LogUtil.Level.ERROR,"微信工具类:解析xml关闭通道异常",m);
                throw  new AppException("微信工具类:解析xml关闭通道异常");
            }

        }
        return map;

    }

    /**
     * 将map转换成xml
     * @param map
     * @return xml字符串
     * @throws Exception
     */
    public static String getXmlFromMap(Map<String,? extends Object> map)throws Exception{
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");

        Set<String> set = map.keySet();
        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            String key = it.next();
            if(!key.equals("class")){
                sb.append("<"+key+">").append(map.get(key)).append("</"+key+">");
            }

        }

        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 将对象转换成map
     */
    public static <T> Map<String, Object> beanToMap(T bean) throws  Exception{

        Map<String, Object> map = new HashMap();
        if (bean != null) {
            map= BeanUtils.describe(bean);
        }
        return map;
    }

}
