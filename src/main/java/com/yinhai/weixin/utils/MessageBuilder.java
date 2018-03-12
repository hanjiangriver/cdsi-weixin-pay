package com.yinhai.weixin.utils;

/**
 * 消息构建器
 * Created by 张汉江 on 2018/3/11
 */
public class MessageBuilder {

    private StringBuilder builder;
    public MessageBuilder(){
        builder=new StringBuilder();
    }

    public MessageBuilder(int capacity){
        builder=new StringBuilder(capacity);
    }

    public MessageBuilder(String str){
       builder=new StringBuilder(str);
    }

    //这个一般都是 传入</xml>
    public void append(String str){
      builder.append(str);
    }
    //这个一般都是 传入<xml>
    public void insert(String str){
      builder.insert(0,str);
    }

    //在整个 结构里面  外面包裹一层 <xml></xml>
    public void surroundWith(String tag){
        StringBuilder sb = new StringBuilder(builder.capacity() + tag.length()
                * 2 + 5);
        sb.append("<").append(tag).append(">\n").append(builder)
                .append("</").append(tag).append(">\n");
        builder=sb;
    }

    public void addTag(String tag,String text){
        if(null==text){
            return;
        }
      builder.append("<").append(tag).append(">").append(text).
              append("</").append(tag).append(">\n");
    }

    public void addData(String tag,String data){
      if(data==null){
          return;
      }
        builder.append("<").append(tag).append("><![CDATA[").append(data).
                append("]]></").append(tag).append(">\n");
    }


    public void addData(String tag,Long data){
        if(data==null){
            return;
        }
        builder.append("<").append(tag).append(">").append(data).
                append("</").append(tag).append(">\n");
    }

    public void addData(String tag,Integer data){
        if(data==null){
            return;
        }
        builder.append("<").append(tag).append(">").append(data).
                append("</").append(tag).append(">\n");
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
