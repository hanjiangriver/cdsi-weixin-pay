package com.yinhai.weixin.exception;

/**
 * Created by 张汉江 on 2018/3/7
 */
public class QueryException extends RuntimeException {

    private static final long serialVersionUID=1L;


    public  QueryException(){};

    public QueryException(String message){
        super(message);
        errorMessage=message;
    }

    public QueryException(String message,String fieldName){
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
