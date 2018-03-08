package com.yinhai.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 张汉江 on 2018/3/7
 */
@Controller
public class TestController {

  //  @ResponseBody
    @RequestMapping(value = "/page/get")
    public  ModelAndView test(HttpSession session,String log){
        System.out.println(session.getAttribute("name"));
        ModelAndView mv=new ModelAndView("hospital/index");
        //ModelAndView mv=new ModelAndView("index");
        return  mv;
    }

    @RequestMapping(value = "/view/post")
    public  ModelAndView post(){
        System.out.println("我是post");
        return new ModelAndView("home");
    }
}
