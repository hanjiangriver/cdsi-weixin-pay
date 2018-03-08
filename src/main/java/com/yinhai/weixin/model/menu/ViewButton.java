package com.yinhai.weixin.model.menu;

/**
 * Created by 张汉江 on 2018/3/4
 */
public class ViewButton extends  Button{
    //类型 view
    private  String type;

    //地址 要跳转的
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ViewButton{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
