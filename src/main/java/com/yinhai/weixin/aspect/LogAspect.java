package com.yinhai.weixin.aspect;


import com.yinhai.weixin.utils.LogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 面向切面的编程
 * Created by 张汉江 on 2018/3/7
 */
//@Aspect
@Component
public class LogAspect {
    // 第一个“*”表示 方法的访问权限 比如 public private等
    //第二个“*”表示 该包下的所有方法
    @Before("execution(* com.yinhai.weixin.controller.TestController.*(..))")
    public void beforeMethod(JoinPoint joinPoint){
        LogUtil.show(LogUtil.Level.INFO,"进入包:"+
                joinPoint.getSignature().getDeclaringTypeName()+"的"+joinPoint.getSignature().getName()+"方法");

    }
}
