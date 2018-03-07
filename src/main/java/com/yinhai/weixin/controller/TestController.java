package com.yinhai.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 张汉江 on 2018/3/7
 */
@Controller
public class TestController {
    @RequestMapping(value = "/page/get")
    @ResponseBody
    public  String test(HttpSession session,String log){

        System.out.println(session.getAttribute("name"));
        return "张汉江";
    }

    @RequestMapping(value = "/view/post")
    @ResponseBody
    public  String post(){
        System.out.println("我是post");
        return "post";
    }
}
