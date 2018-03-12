package com.yinhai.weixin.model.message;

import com.yinhai.weixin.validation.validateinterface.NotNullAnnotionInterface;

/**
 * 音乐消息
 * Created by 张汉江 on 2018/3/11
 */
public class MusicMessage extends BasicMessage {
    //素材id
    @NotNullAnnotionInterface(message = "素材id不能为空")
    private  String thumbMediaId;

    //音乐标题 可为空
    private  String title;

    //音乐描述 可为空
    private  String description;

   // 音乐连接 可为空
    private  String musicURL  ;

    //高质量音乐链接，WIFI环境优先使用该链接播放音乐
    private  String hQMusicUrl;

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

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

    public String getMusicURL() {
        return musicURL;
    }

    public void setMusicURL(String musicURL) {
        this.musicURL = musicURL;
    }

    public String gethQMusicUrl() {
        return hQMusicUrl;
    }

    public void sethQMusicUrl(String hQMusicUrl) {
        this.hQMusicUrl = hQMusicUrl;
    }
}
