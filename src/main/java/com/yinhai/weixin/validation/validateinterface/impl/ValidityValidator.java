package com.yinhai.weixin.validation.validateinterface.impl;

import com.yinhai.weixin.validation.validateinterface.ValidityAnnotionInterface;
import sun.awt.SunHints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 张汉江 on 2018/3/7
 */
public class ValidityValidator implements ConstraintValidator<ValidityAnnotionInterface,String> {

    private String reg;

    @Override
    public void initialize(ValidityAnnotionInterface validityAnnotionInterface) {
       reg=validityAnnotionInterface.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
       if(null==s||s.trim().length()==0){
           return  false;
       }
        Pattern pattern=Pattern.compile(reg);
        Matcher matcher=pattern.matcher(s);//主要用来判断 是否与给定的值相等

        return matcher.matches();
    }
}
