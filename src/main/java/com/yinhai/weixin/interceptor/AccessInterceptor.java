package com.yinhai.weixin.interceptor;

import com.yinhai.weixin.utils.LogUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 是否允许访问  设置seesion值
 * Created by 张汉江 on 2018/3/7
 */
@Component
public class AccessInterceptor implements HandlerInterceptor {

    /**
     * 只有当返回是true 的时候才 能访问方法
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        LogUtil.show(LogUtil.Level.INFO,"进入拦截器AccessInterceptor的preHandle方法中");
        HttpSession session=httpServletRequest.getSession();
        session.setAttribute("name","zhanghj");
        return true;
    }

    /**
     * 这个方法是当链路上的所有 preHandle返回true  该方法才会 都会执行
     * 并且是 在controller中的方法执行完成以后才会执行
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
      //System.out.println("postHandle");
    }

    /**
     * 这个方法是当自己的 preHandle返回true  不管其他拦截器的preHandle返回是否为true 都会执行
     * 当自己的 preHandle返回false  该方法不会执行
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
       // System.out.println("afterCompletion");
    }
}
