package com.yinhai.weixin.model.message;

import com.yinhai.weixin.validation.validateinterface.NotNullAnnotionInterface;

import javax.validation.constraints.NotNull;

/**
 * 视频消息
 * Created by 张汉江 on 2018/3/11
 */
public class VideoMessage extends BasicMessage {


    //素材id
    @NotNullAnnotionInterface(message = "素材id不能为空")
    private  String mediaId;

    //视频消息的标题 可为空
    private  String title;

    //视频消息的描述 可为空
    private  String description;


    @Override
    public String toString() {
        return "VideoMessage{" +
                "mediaId='" + mediaId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                "} " + super.toString();
    }
}
