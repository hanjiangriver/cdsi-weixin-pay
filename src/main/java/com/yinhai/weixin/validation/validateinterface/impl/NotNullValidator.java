package com.yinhai.weixin.validation.validateinterface.impl;

import com.yinhai.weixin.validation.validateinterface.NotNullAnnotionInterface;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by 张汉江 on 2018/3/7
 */
public class NotNullValidator implements ConstraintValidator<NotNullAnnotionInterface,String> {


    @Override
    public void initialize(NotNullAnnotionInterface notNullAnnotionInterface) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(null==s||s.trim().length()==0){
            return false;
        }else{
            return true;
        }
    }
}
