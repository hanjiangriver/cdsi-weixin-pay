package com.yinhai.weixin.configuration;

/**
 * 消息类型
 * Created by 张汉江 on 2018/3/11
 */
public interface MessageConfig {

    /**
     * 返回消息类型：文本
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * 返回消息类型：音乐
     */
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

    /**
     * 返回消息类型：图文
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * 请求消息类型：文本
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";

    /**
     * 请求消息类型：图片
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

    /**
     * 请求消息类型：链接
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    /**
     * 请求消息类型：地理位置
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

    /**
     * 请求消息类型：音频
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

    /**
     * 请求消息类型：推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * 事件类型：subscribe(订阅)
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型：unsubscribe(取消订阅)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";


    public static final String TO_USER_NAME  = "ToUserName";

    public static final String FROM_USER_NAME  = "FromUserName";

    public static final String CREATE_TIME  = "CreateTime";

    public static final String MSG_TYPE  = "MsgType";

    public static final String CONTENT  = "Content";

    public static final String ARTICLE_COUNT  = "ArticleCount";

    public static final String TITLE  = "Title";

    public static final String DESCRIPTION  = "Description";

    public static final String PIC_URL  = "PicUrl";

    public static final String URL  = "Url";

    public static final String XML_BEGIN  = "<xml>";

    public static final String XML_END  = "</xml>";

    public static final String ARTICLES_BEGIN  = "<Articles>";

    public static final String ARTICLES_END  = "</Articles>";

    public static final String ITEM_BEGIN  = "<item>";

    public static final String ITEM_END  = "</item>";

    public static final String IMAGE_JPG  = ".jpg";

    public static final String UPLOAD  = "upload/";
}
