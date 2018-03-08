package com.yinhai.weixin.service;

import com.alibaba.fastjson.JSONObject;
import com.yinhai.weixin.common.GetAceessToken;
import com.yinhai.weixin.configuration.AppConfig;
import com.yinhai.weixin.configuration.WeiXinServerConstant;
import com.yinhai.weixin.model.AccessToken;

import com.yinhai.weixin.model.menu.Button;
import com.yinhai.weixin.model.menu.MainMenu;
import com.yinhai.weixin.model.menu.ViewButton;
import com.yinhai.weixin.utils.HttpsUtil;
import com.yinhai.weixin.utils.LogUtil;
import org.springframework.stereotype.Service;

/**
 * Created by 张汉江 on 2018/3/3
 */
@Service
public class MenuService {
    /**
     * 生成菜单
     */
    public  int productMenu() {

        //调用接口凭证
      AccessToken accessToken= GetAceessToken.getAccessToken(WeiXinServerConstant.APP_ID,WeiXinServerConstant.APP_SECRET);
        LogUtil.show(LogUtil.Level.INFO, JSONObject.toJSONString(accessToken));
       //创建菜单
       // String acess_token="7_p4U7R45xBz8bKPLSU044hyt01HqP310wb7VGmagW81" +
          //      "qXa9oNghYdK3cUZSKhwmj8bmXYWZPuHkSCTSgQrHSTZTULGOik7M0dfWJwItTxLPM6hSMkn3B5UGFsUYcNKNdAIAAXA";
        int result= creatMenu(getMenu(),accessToken.getToken());
       // int result= WeiXinUtil.creatMenu(getMenu(),acess_token);
        // 判断菜单创建结果
        if (0 == result)
            LogUtil.show(LogUtil.Level.INFO, "菜单创建成功！");
        else
            LogUtil.show(LogUtil.Level.INFO, "菜单创建失败！");
         return  result;

    }
    //获取菜单
    private MainMenu getMenu(){

        /********* view的视图   *************************/
        ViewButton vbtn1=new ViewButton();
        vbtn1.setType("view");
        vbtn1.setName("挂号");
        vbtn1.setUrl(AppConfig.gh_81gk_url);

        ViewButton vbtn2=new ViewButton();
        vbtn2.setType("view");
        vbtn2.setName("诊间支付");
        vbtn2.setUrl(AppConfig.zj_81gk_url);

        ViewButton vbtn3=new ViewButton();
        vbtn3.setType("view");
        vbtn3.setName("我的a");
        vbtn3.setUrl(AppConfig.my_url);
        /********* view的视图   *************************/

        MainMenu menu=new MainMenu();
        menu.setButton(new Button[]{vbtn1,vbtn2,vbtn3});

        LogUtil.show(LogUtil.Level.INFO, "menu的值："+JSONObject.toJSONString(menu));
        return  menu;
    }

    /**
     * 创建菜单
     * @param menu
     * @param accessToken 有效凭证
     * @return 0 表示成功 其他表示失败
     */
    private static int creatMenu(MainMenu menu,String accessToken){
        int result=0;
        //拼接菜单url
        String url= AppConfig.menu_create_url.replace("ACCESS_TOKEN",accessToken);
        //将菜单对象转换成json
        String jsonMenu=JSONObject.toJSONString(menu);
        System.out.println("菜单json"+jsonMenu);
        // 调用接口创建菜单
        JSONObject jsonObject= HttpsUtil.httpRequest(url,"POST",jsonMenu);
        if(null!=jsonObject){
            if (0 != jsonObject.getIntValue("errcode")) {
                result = jsonObject.getIntValue("errcode");
                LogUtil.show(LogUtil.Level.INFO, "创建菜单失败"+jsonObject.getString("errmsg"));
            }
        }
        return result;
    }
}
