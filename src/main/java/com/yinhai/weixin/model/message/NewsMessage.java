package com.yinhai.weixin.model.message;

import com.yinhai.weixin.validation.validateinterface.NotNullAnnotionInterface;

import java.util.List;

/**
 * 图文消息
 * Created by 张汉江 on 2018/3/11
 */
public class NewsMessage extends BasicMessage {

    //图文消息个数，限制为8条以内
    @NotNullAnnotionInterface(message = "图文消息个数不能为空")
    private  int articleCount;

    //多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应
    @NotNullAnnotionInterface(message = "图文消息信息不能为空")
    private List<Article> articles ;


    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "NewsMessage{" +
                "articleCount='" + articleCount + '\'' +
                ", articles=" + articles +
                "} " + super.toString();
    }
}
