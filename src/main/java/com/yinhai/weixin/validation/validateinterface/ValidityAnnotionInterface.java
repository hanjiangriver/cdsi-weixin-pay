package com.yinhai.weixin.validation.validateinterface;

import com.yinhai.weixin.validation.validateinterface.impl.ValidityValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by 张汉江 on 2018/3/7
 */
@Documented
@Target( {ElementType.FIELD,
        ElementType.METHOD,
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidityValidator.class})
public @interface ValidityAnnotionInterface {  //验证传过来的值是否与定义的值（可用正则）相等
    String message() default "参数不合法";

    String value() default "";

    Class<?>[] groups() default {};


    Class<? extends Payload>[] payLoad() default {};
}
