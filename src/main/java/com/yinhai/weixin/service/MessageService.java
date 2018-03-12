package com.yinhai.weixin.service;

import com.yinhai.weixin.configuration.AppConfig;
import com.yinhai.weixin.configuration.MessageConfig;
import com.yinhai.weixin.exception.AppException;
import com.yinhai.weixin.model.message.Article;
import com.yinhai.weixin.model.message.BasicMessage;
import com.yinhai.weixin.model.message.NewsMessage;
import com.yinhai.weixin.model.message.TextMessage;
import com.yinhai.weixin.utils.LogUtil;
import com.yinhai.weixin.utils.MessageUtil;
import com.yinhai.weixin.utils.XmlUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 处理用户发送的消息
 * Created by 张汉江 on 2018/3/8
 */
@Service
public class MessageService {

    //处理传过来的消息
    public String processReq(HttpServletRequest request){
        TextMessage textMessage=null;
        NewsMessage newsMessage=null;
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
            BasicMessage message=new BasicMessage();
            message.setToUserName(fromUserName);//发给谁
            message.setFromUserName(toUserName);//谁发的
            message.setCreateTime(new Date().getTime());//回复时间
            //  textMessage.setMsgType("<![CDATA[text]]>");
            // textMessage.setFuncFlag(0);

            // 如果接收的是文本消息 则发送图文消息
            if (msgType.equalsIgnoreCase(MessageConfig.REQ_MESSAGE_TYPE_TEXT)) {
                newsMessage=new NewsMessage();
                newsMessage.setFromUserName(message.getFromUserName());
                newsMessage.setToUserName(message.getToUserName());
                newsMessage.setCreateTime(message.getCreateTime());
                newsMessage.setArticleCount(2);
                newsMessage.setArticles(getArticles());
                resMessage= MessageUtil.newsMessageToXml(newsMessage);

            }

            // 事件推送
            else if (msgType.equalsIgnoreCase(MessageConfig.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = resMap.get("Event");
                // 订阅
                if (eventType.equalsIgnoreCase( MessageConfig.EVENT_TYPE_SUBSCRIBE)) {
                    textMessage=new TextMessage();
                    textMessage.setFromUserName(message.getFromUserName());
                    textMessage.setToUserName(message.getToUserName());
                    textMessage.setCreateTime(message.getCreateTime());
                    respContent = "谢谢您的关注！目前该公众号正在开发中，敬请期待";
                    textMessage.setContent(respContent);
                    resMessage=MessageUtil.textMessageToXml(textMessage);
                }
                // 取消订阅
                else if (eventType.equalsIgnoreCase( MessageConfig.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                    respContent = "";
                }
                // 自定义菜单点击事件
                else if (eventType.equalsIgnoreCase( MessageConfig.EVENT_TYPE_CLICK)) {
                    // 事件KEY值，与创建自定义菜单时指定的KEY值对应
                    String eventKey = resMap.get("EventKey");
                    if (eventKey.equals("11")) {
                        respContent = "天气预报菜单项被点击！";
                    }
                }
            }
            LogUtil.show(LogUtil.Level.INFO,"回复的消息resMessage:"+resMessage);

        } catch (Exception e) {
            LogUtil.show(LogUtil.Level.INFO,"生成消息格式或解析消息发生错误",e);
            throw  new AppException("生成消息格式或解析消息发生错误");
        }
        return resMessage ;


    }

    //得到Articles
    private List<Article> getArticles(){
        List<Article> articles=new ArrayList<>();
        articles.add(getArticle("挂号不排队——预约挂号","http://img2.imgtn.bdimg.com/it/u=2360079417,4049234709&fm=27&gp=0.jpg", AppConfig.gh_81gk_url,""));
        articles.add(getArticle("诊间快速付——诊间支付","", AppConfig.zj_81gk_url,""));
        return articles;
    }
    //得到Article
    private Article getArticle(String title, String picUrl,String url,String description){
       Article article=new Article();
       article.setDescription(description);
       article.setPicUrl(picUrl);
       article.setTitle(title);
       article.setUrl(url);
       return article;
    }

}
