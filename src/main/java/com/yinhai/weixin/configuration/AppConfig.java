package com.yinhai.weixin.configuration;

/**
 * Created by 张汉江 on 2018/3/4
 */
public class AppConfig {
    //获取access_token
    public static final  String access_token_url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    //创建自定义菜单
    public static final String menu_create_url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    //挂号支付
    public static final  String gh_81gk_url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb353482615a0bc7e&redirect_uri=http%3a%2f%2fcdwx.cdo2o.cn%2fcdsi-wxpay%2fwechatpay%2fpage%2fmenu_registration.do&response_type=code&scope=snsapi_userinfo#wechat_redirect";

    //诊间支付
    public static final  String zj_81gk_url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb353482615a0bc7e&redirect_uri=http%3a%2f%2fcdwx.cdo2o.cn%2fcdsi-wxpay%2fwechatpay%2fpage%2fzjzf_pay_choose.do&response_type=code&scope=snsapi_userinfo#wechat_redirect";

    //我的
    public static final  String my_url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb353482615a0bc7e&redirect_uri=http%3a%2f%2fcdwx.cdo2o.cn%2fcdsi-wxpay%2fwechatpay%2fpage%2fuser.do&response_type=code&scope=snsapi_userinfo#wechat_redirect";

    //验证服务器的token
    public static final String token="yinhai2018";


     // 图片消息XML数据格式
    public static final  String imageMessage="<xml>\n" +
             "               <ToUserName><![CDATA[%s]]></ToUserName>\n" +
             "               <FromUserName><![CDATA[%s]]></FromUserName>\n" +
             "               <CreateTime>%s</CreateTime>\n" +
             "               <MsgType><![CDATA[image]]></MsgType>\n" +
             "              <Image>\n" +
             "               <MediaId><![CDATA[%s]]></MediaId>\n" +
             "              </Image>\n" +
             "             </xml>";

    public static final  String contentMessage= "<xml>" +
            "<ToUserName><![CDATA[%s]]></ToUserName>" +
            "<FromUserName><![CDATA[%s]]></FromUserName>" +
            "<CreateTime>%s</CreateTime>" +
            "<MsgType><![CDATA[text]]></MsgType>" +
            "<Content><![CDATA[%s]]></Content>" +
            "</xml>";



}
