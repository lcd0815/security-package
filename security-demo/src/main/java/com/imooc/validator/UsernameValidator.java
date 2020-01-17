package com.imooc.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * lcd  2020/1/1
 * Description:
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {FieldValueConstraintFirst.class})
public @interface UsernameValidator {
    String message() default "{Username校验不通过}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
