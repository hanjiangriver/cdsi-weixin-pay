package com.yinhai.weixin.service;

import com.yinhai.weixin.utils.XmlUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 处理用户发送的消息
 * Created by 张汉江 on 2018/3/8
 */
@Service
public class MessageService {

    //处理传过来的消息
    public String processReq(HttpServletRequest request){
        String resMessage=null;//响应的消息格式
        try {
            // 默认返回的文本消息内容
            String respContent = "请求处理异常，请稍候尝试！";
            Map<String,String> resMap= XmlUtil.parseXmlToMap(request.getInputStream());

            // 发送方帐号（open_id）
            String fromUserName = resMap.get("FromUserName");
            // 公众帐号
            String toUserName = resMap.get("ToUserName");
            // 消息类型
            String msgType = resMap.get("MsgType");

            //消息内容
            String content=resMap.get("Content");

            //媒体类型id
            String MediaId=resMap.get("MediaId");
            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setContent(respContent);
            //  textMessage.setMsgType("<![CDATA[text]]>");
            // textMessage.setFuncFlag(0);

            // 文本消息
            if (msgType.equalsIgnoreCase(MessageType.TEXT)&&"你好".endsWith(content)) {
                respContent ="听说你昨天去旅游了，肯定拍了照片吧，很想看一看呢，如果你不发照片我会一直重复这句话的,hahaha!";
                textMessage.setContent(respContent);
            }
            // 图片消息
            else if (msgType.equalsIgnoreCase(MessageType.IMAGE)) {
                respContent = "好看！";
                textMessage.setMediaId(MediaId);
            }
            // 地理位置消息
            else if (msgType.equalsIgnoreCase(MessageType.LOCATION)) {
                respContent = "原来你在这里，好想去看看！";
                textMessage.setContent(respContent);
            }
            // 链接消息
            else if (msgType.equalsIgnoreCase(MessageType.LINK)) {
                respContent = "您发送的是链接消息！";
            }
            // 音频消息
            else if (msgType.equalsIgnoreCase(MessageType.VOICE)) {
                respContent = "终于听到你的声音了！";
                textMessage.setContent(respContent);
            }
            // 事件推送
            else if (msgType.equalsIgnoreCase(MessageType.EVENT)) {
                // 事件类型
                String eventType = resMap.get("Event");
                // 订阅
                if (eventType.equalsIgnoreCase( MessageType.SUBSCRIBE)) {
                    respContent = "谢谢您的关注！目前该公众号正在开发中，敬请期待";
                    textMessage.setContent(respContent);
                }
                // 取消订阅
                else if (eventType.equalsIgnoreCase( MessageType.UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                    respContent = "";
                }
                // 自定义菜单点击事件
                else if (eventType.equalsIgnoreCase( MessageType.CLICK)) {
                    // 事件KEY值，与创建自定义菜单时指定的KEY值对应
                    String eventKey = resMap.get("EventKey");

                    if (eventKey.equals("11")) {
                        respContent = "天气预报菜单项被点击！";
                    }
                }
            }
            if (msgType.equalsIgnoreCase(MessageType.IMAGE)) {
                resMessage=String.format(Config.imageMessage,
                        textMessage.getToUserName(),textMessage.getFromUserName(),textMessage.getCreateTime(),textMessage.getMediaId());
            }else if(msgType.equalsIgnoreCase(MessageType.TEXT)&&"图文".endsWith(content)){
                resMessage=buildNewsMessage(resMap);
            }
            else{
                resMessage=String.format(Config.contentMessage,
                        textMessage.getToUserName(),textMessage.getFromUserName(),textMessage.getCreateTime(),textMessage.getContent());
            }
            //  textMessage.setContent(respContent);
            //  resMessage= XmlUtil.getXMLFromMap(XmlUtil.beanToMap(textMessage));
            //  resMessage=String.format(Config.contentMessage,
            //        textMessage.getToUserName(),textMessage.getFromUserName(),textMessage.getCreateTime(),textMessage.getContent());
            logger.info("回复的消息resMessage:"+resMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMessage ;


    }

    /**
     * 构造图文消息
     * @param map 封装了解析结果的Map
     * @return 图文消息XML字符串
     */
    private  String buildNewsMessage(Map<String, String> map) {
        String fromUserName = map.get("FromUserName");
        // 开发者微信号
        String toUserName = map.get("ToUserName");
        NewsMessage item=new NewsMessage();
        item.setTitle("挂号不排队——预约挂号");
        /*item.setDescription("工欲善其事，必先利其器。要做微信公众号开发，那么要先准备好两样必不可少的东西：\n" +
                "\n" +
                "　　1、要有一个用来测试的公众号。\n" +
                "\n" +
                "　　2、用来调式代码的开发环境");*/
        // item.setPicUrl("http://images2015.cnblogs.com/blog/289233/201601/289233-20160121164317343-2145023644.png");
        item.setPicUrl("http://img2.imgtn.bdimg.com/it/u=2360079417,4049234709&fm=27&gp=0.jpg");
        item.setUrl(Config.gh_81gk_url);
        // item.setUrl("http://www.cnblogs.com/xdp-gacl/p/5149171.html");

        String itemContent1 = buildSingleItem(item);

        NewsMessage item2 = new NewsMessage();
        item2.setTitle("诊间快速付——诊间支付");
        // item2.setDescription("微信服务器就相当于一个转发服务器，终端（手机、Pad等）发起请求至微信服务器，微信服务器然后将请求转发给我们的应用服务器。应用服务器处理完毕后，将响应数据回发给微信服务器，微信服务器再将具体响应信息回复到微信App终端。");
        item2.setPicUrl("");
        item2.setUrl(Config.zj_81gk_url);
        String itemContent2 = buildSingleItem(item2);


        String content = String.format("<xml>\n" +
                "<ToUserName><![CDATA[%s]]></ToUserName>\n" +
                "<FromUserName><![CDATA[%s]]></FromUserName>\n" +
                "<CreateTime>%s</CreateTime>\n" +
                "<MsgType><![CDATA[news]]></MsgType>\n" +
                "<ArticleCount>%s</ArticleCount>\n" +
                "<Articles>\n" + "%s" +
                "</Articles>\n" +
                "</xml> ", fromUserName, toUserName, new Date().getTime(), 2, itemContent1 + itemContent2);
        return content;

    }

    /**
     * 生成图文消息的一条记录
     *
     * @param item
     * @return
     */
    private  String buildSingleItem(NewsMessage item) {
        String itemContent = String.format("<item>\n" +
                "<Title><![CDATA[%s]]></Title> \n" +
                "<Description><![CDATA[%s]]></Description>\n" +
                "<PicUrl><![CDATA[%s]]></PicUrl>\n" +
                "<Url><![CDATA[%s]]></Url>\n" +
                "</item>", item.getTitle(), item.getDescription(), item.getPicUrl(), item.getUrl());
        return itemContent;
    }
}
