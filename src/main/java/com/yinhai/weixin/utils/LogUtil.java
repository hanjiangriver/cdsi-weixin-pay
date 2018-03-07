package com.yinhai.weixin.utils;


import org.apache.log4j.Logger;

/**
 * 日志工具
 * Created by 张汉江 on 2018/3/7
 */
public class LogUtil {

    private  static  final Logger log= Logger.getLogger(LogUtil.class);

    /**
     * 不允许实例化工具类
     */
    private LogUtil(){
        throw  new IllegalAccessError("Utility class");
    }

    /**
     * 日志级别 默认trace
     */
    public enum  Level{
        INFO,DEBUG,ERROR,WARN,FATAL,TRACE;
    }

    /**
     * 记录异常
     * @param level 日志级别
     * @param message 日志内容
     * @param throwable 异常内容
     */
    public static  void show(Level level,Object message,Throwable throwable){
        show(level,message,throwable,true);
    }
    /**
     * 正常记录日志
     * @param level 日志级别
     * @param message 日志内容
     */
    public static void show(Level level,Object message){
       show(level,message,null,false);
    }
    /**
     *  该方法是私有的
     * @param level 日志级别
     * @param message  日志内容
     * @param throwable  异常
     * @param showException 是否显示异常
     */
    private static  void show(Level level ,Object message,Throwable throwable,boolean showException){
      switch (level){
          case DEBUG:
              if(showException){
                  log.debug(message,throwable);
              }else {
                  log.debug(message);
              }
              break;
          case INFO:
              if(showException){
                  log.info(message,throwable);
              }else {
                  log.info(message);
              }
              break;
          case WARN:
              if(showException){
                  log.warn(message,throwable);
              }else {
                  log.warn(message);
              }
              break;
          case ERROR:
              if(showException){
                  log.error(message,throwable);
              }else{
                  log.error(message);
              }
              break;
          case FATAL:
              if(showException){
                  log.fatal(message,throwable);
              }else {
                  log.fatal(message);
              }
              break;
          default:
              if(showException){
                  log.trace(message,throwable);
              }else {
                  log.trace(message);
              }
              break;
      }
   }
}
