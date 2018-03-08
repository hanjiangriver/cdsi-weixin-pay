package com.yinhai.weixin.configuration;

/**
 * Created by 张汉江 on 2018/1/6
 */
public class WeiXinServerConstant {

    public static final String REMOTE_ADDR = "125.69.67.30";
    // public static final String REMOTE_ADDR="118.122.119.153";//图书馆ip
    /*
     * 公众号AppId 开发者id
     */
    public static final String APP_ID = "wxb353482615a0bc7e";

    /**
     * 公众号AppSecret
     */
    public static final String APP_SECRET = "4a34fc2d7cdcc431d4077656e274bfe2";
    /**
     * 微信支付商户号
     */
    //public static final String MCH_ID = "1482103082"; //这个是普通商户号
    public static final String MCH_ID = "1482434112";//这个是服务商商户号
    /**
     * 微信支付子商户号  普通商户号下  不允许传该字段
     */
    public static final String SUB_MCH_ID = "1497235392";
    /**
     * 微信支付API秘钥
     */
    public static final String KEY = "QBmgXTJP7QDTudt42qN4Jy2zucyinhai";
    /**
     * 微信交易类型:公众号支付
     */
    public static final String TRADE_TYPE_JSAPI = "JSAPI";
    /**
     * 支付设备
     */
    public static final String WEB = "WEB";
    /**
     * 返回成功字符串
     */
    public static final String RETURN_SUCCESS = "SUCCESS";
    /**
     * 支付地址  redirect_uri是回调地址 就是统一下单接口（自己定义的）
     */
    //public static final String PAY_URL="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb353482615a0bc7e&redirect_uri=http%3a%2f%2f194391mh05.imwork.net%2fweixin%2fyinhai%2funifiedOrder&response_type=code&scope=snsapi_base#wechat_redirect";
    public static final String PAY_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb353482615a0bc7e&redirect_uri=http%3a%2f%2fcdwx.cdo2o.cn%2fcdsi-wxpay%2fyinhai%2funifiedOrder&response_type=code&scope=snsapi_userinfo#wechat_redirect";
    /**
     * 统一下单地址   调用微信平台的接口
     */
    public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * 退款地址
     */
    public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    /**
     * 支付通知地址
     */
    // public static final String NOTITY_URL="http://194391mh05.imwork.net/wexin/yinhai/payCallback";
    public static final String NOTITY_URL = "http://cdwx.cdo2o.cn/cdsi-wxpay/yinhai/payCallback";
    /**
     * 证书位置
     */
    public static final String CERT_PATH = "E:/weixin/src/main/webapp/WEB-INF/cert/apiclient_cert.p12";

    //支付以后微信通知的地址
    public static final  String PAYBACK_URL="http://194391mh05.imwork.net/cdsi-wxpay/yinhai/payCallback";

    //获取code的地址
    public static final String in_payUrl =

            //"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb353482615a0bc7e&redirect_uri=http%3a%2f%2f194391mh05.imwork.net%2fweixin%2fyinhai%2funifiedOrder&response_type=code&scope=snsapi_userinfo#wechat_redirect";
            "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb353482615a0bc7e&redirect_uri=http%3a%2f%2fcdwx.cdo2o.cn%2fcdsi-wxpay%2fyinhai%2funifiedOrder&response_type=code&scope=snsapi_userinfo#wechat_redirect";


    public static final String SUCCESS_XML = "<xml><return_code><![CDATA[SUCCESS]]>" +
            "</return_code> <return_msg><![CDATA[OK]]></return_msg></xml>";

    public static final String FAIL_XML = "<xml><return_code><![CDATA[FAIL]]></return_code>"
            + "<return_msg><![CDATA[NO]]></return_msg></xml>";

    /**
     * 通过code来生成能够获取授权access_token的URL
     *
     * @param code 获取到的code码
     * @return
     */
    public static String Authtoken_URL(String code) {
        StringBuffer url = new StringBuffer();
        url.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
        url.append(WeiXinServerConstant.APP_ID);
        url.append("&secret=");
        url.append(WeiXinServerConstant.APP_SECRET);
        url.append("&code=");
        url.append(code);
        url.append("&grant_type=authorization_code");
        return url.toString();
    }

}
