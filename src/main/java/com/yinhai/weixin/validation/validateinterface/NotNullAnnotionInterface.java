package com.yinhai.weixin.validation.validateinterface;

import org.hibernate.validator.internal.constraintvalidators.bv.NotNullValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.groups.Default;
import java.lang.annotation.*;

/**
 * Created by 张汉江 on 2018/3/7
 */
@Documented
@Target({
        ElementType.FIELD,
        ElementType.METHOD,
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NotNullValidator.class})
public @interface NotNullAnnotionInterface {  //判断传过来的值是否为空
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payLoad()  default  {};
}
