package com.yinhai.weixin.controller;

import com.alibaba.fastjson.JSONObject;
import com.yinhai.weixin.exception.AppException;
import com.yinhai.weixin.service.MenuService;
import com.yinhai.weixin.service.MessageService;
import com.yinhai.weixin.utils.LogUtil;
import com.yinhai.weixin.utils.ServerSignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 程序统一入口  所有的消息由此转发和处理
 * Created by 张汉江 on 2018/3/8
 */
@Controller
public class UniAccessController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private MessageService messageService;
    /**
     * 验签  服务器配置
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/info" ,method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String,String[]> map=request.getParameterMap();
        LogUtil.show(LogUtil.Level.INFO,"开始调用验签"+ JSONObject.toJSONString(map));
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out=null;
        if(ServerSignUtil.checkSignature(signature,timestamp,nonce)){
            out=response.getWriter();
            out.print(echostr);  //这句话相当于 @responsebody
            new Thread(new Runnable() {  //开个线程去处理 生成菜单 （子线程的创建和初始化不会阻塞主线程）
                @Override             // 因为菜单的生成需要时间 如果不开个线程处理的话 验证服务器会超时
                public void run() {
                    menuService.productMenu();//生成菜单
                }
            }).start();
          //  LogUtil.show(LogUtil.Level.INFO,"服务器验签成功！");
        }else {
           LogUtil.show(LogUtil.Level.ERROR,"服务器验签失败！");
        }
        if(out!=null){
           out.close();
           out=null;
        }

    }

    @RequestMapping(value="/info",method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //处理消息
        //防止乱码  响应和请求都设置了字符集
        LogUtil.show(LogUtil.Level.INFO,"开始处理消息");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //处理消息
        String reqMessage=messageService.processReq(request);
        PrintWriter out=null;
        try {
            out=response.getWriter();
            out.print(reqMessage);
            out.flush();
        } catch (IOException e) {
            LogUtil.show(LogUtil.Level.ERROR,"处理消息发生异常",e);
            throw new AppException("处理消息发生异常");
        }finally {
            if(out!=null){
                out.close();
            }
        }
    }
}
