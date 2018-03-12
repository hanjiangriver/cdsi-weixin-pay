package com.yinhai.weixin.utils;

import com.yinhai.weixin.configuration.MessageConfig;
import com.yinhai.weixin.model.message.Article;
import com.yinhai.weixin.model.message.NewsMessage;
import com.yinhai.weixin.model.message.TextMessage;

import java.util.List;

/**
 * 消息工具类
 * Created by 张汉江 on 2018/3/11
 */
public class MessageUtil implements MessageConfig{

    /**
     * 文本消息对象转换成xml
     *
     * @param textMessage
     *            文本消息对象
     * @return xml
     */
    public static String textMessageToXml(TextMessage textMessage) {
        MessageBuilder mb = new MessageBuilder();
        mb.insert(XML_BEGIN);
        mb.addData(TO_USER_NAME, textMessage.getToUserName());
        mb.addData(FROM_USER_NAME, textMessage.getFromUserName());
        mb.addData(CREATE_TIME, textMessage.getCreateTime());
        mb.addData(MSG_TYPE, REQ_MESSAGE_TYPE_TEXT);
        mb.addData(CONTENT, textMessage.getContent());
        mb.append(XML_END);
        return mb.toString();
    }

    /**
     * 音乐消息对象转换成xml
     *
     * @param musicMessage
     *            音乐消息对象
     * @return xml
     *
     *         public static String musicMessageToXml(MusicMessage musicMessage)
     *         { xstream.alias("xml", musicMessage.getClass()); return
     *         xstream.toXML(musicMessage); }
     */
    /**
     * 图文消息对象转换成xml
     *
     * @param newsMessage
     *            图文消息对象
     * @return xml
     */
    public static String newsMessageToXml(NewsMessage newsMessage) {
        MessageBuilder mb = new MessageBuilder();
        mb.insert(XML_BEGIN);
        mb.addData(TO_USER_NAME, newsMessage.getToUserName());
        mb.addData(FROM_USER_NAME, newsMessage.getFromUserName());
        mb.addData(CREATE_TIME, newsMessage.getCreateTime());
        mb.addData(ARTICLE_COUNT, newsMessage.getArticleCount());
        mb.addData(MSG_TYPE, REQ_MESSAGE_TYPE_TEXT);
        List<Article> list = newsMessage.getArticles();
        mb.insert(ARTICLES_BEGIN);
        for (Article article : list) {
            mb.insert(ITEM_BEGIN);
            mb.addData(TITLE, article.getTitle());
            mb.addData(DESCRIPTION, article.getDescription());
            mb.addData(PIC_URL, article.getPicUrl());
            mb.addData(URL, article.getUrl());
            mb.append(ITEM_END);
        }
        mb.append(ARTICLES_END);
        mb.append(XML_END);
        return mb.toString();
    }
}
