package com.yinhai.weixin.model.message;

/**
 * 文本消息
 * Created by 张汉江 on 2018/3/11
 */
public class TextMessage extends BasicMessage {
    //消息内容
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "content='" + content + '\'' +
                "} " + super.toString();
    }
}
