package com.yinhai.weixin.exception;

/**
 * 自定义异常
 * Created by 张汉江 on 2018/3/7
 */
public class AppException extends RuntimeException {
    private static final long serialVersionUID=1L;


    public  AppException(){};

    public AppException(String message){
        super(message);
        errorMessage=message;
    }

    public AppException(String message,String fieldName){
        super(message);
        errorMessage=message;
        this.fieldName=fieldName;
    }
    //错误消息
    private String errorMessage;

    private String fieldName;

    public String getErrorMessage() {
        return errorMessage;
    }


    public String getFieldName() {
        return fieldName;
    }

}
