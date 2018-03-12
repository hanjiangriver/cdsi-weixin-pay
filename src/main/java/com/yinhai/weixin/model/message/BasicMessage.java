package com.yinhai.weixin.model.message;

import com.yinhai.weixin.validation.validateinterface.NotNullAnnotionInterface;

/**
 * 回复给微信服务器的消息基类
 * Created by 张汉江 on 2018/3/11
 */
public class BasicMessage {

    //开发者微信账号
    @NotNullAnnotionInterface(message = "开发者微信账号不能为空")
    private String fromUserName;

    //接受者微信账号
    @NotNullAnnotionInterface(message = "接受者微信账号不能为空")
    private String toUserName;

    //消息创建时间
    @NotNullAnnotionInterface(message = "消息创建时间不能为空")
    private long  createTime;

    //消息类型
    @NotNullAnnotionInterface(message = "消息类型不能为空")
    private String msgType;

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
