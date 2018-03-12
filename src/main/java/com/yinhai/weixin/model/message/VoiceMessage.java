package com.yinhai.weixin.model.message;

import com.yinhai.weixin.validation.validateinterface.NotNullAnnotionInterface;

/**
 * 语音消息
 * Created by 张汉江 on 2018/3/11
 */
public class VoiceMessage extends  BasicMessage {

    //素材id
    @NotNullAnnotionInterface(message = "素材id不能为空")
    private String mediaId;

    @Override
    public String toString() {
        return "VoiceMessage{" +
                "mediaId='" + mediaId + '\'' +
                "} " + super.toString();
    }
}
