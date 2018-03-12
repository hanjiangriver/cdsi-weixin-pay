package com.yinhai.weixin.model.message;

import com.yinhai.weixin.validation.validateinterface.NotNullAnnotionInterface;

/**
 *这个类是多条图文消息 的内容
 * Created by 张汉江 on 2018/3/11
 */
public class Article {

    //图文消息标题
    @NotNullAnnotionInterface(message = "图文消息标题不能为空")
    private  String title;

    //图文消息描述
    @NotNullAnnotionInterface(message = "图文消息描述不能为空")
    private  String description;

    //图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
    @NotNullAnnotionInterface(message = "图片链接不能为空")
    private  String picUrl;

    //点击图文消息跳转链接
    @NotNullAnnotionInterface(message = "图文消息跳转链接不能为空")
    private  String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
