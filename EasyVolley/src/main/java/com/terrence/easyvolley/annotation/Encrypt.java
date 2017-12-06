package com.terrence.easyvolley.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A field decorated by this annotation with true value will be involved in signing process.
 * Created by DarkSouls on 2017/12/22.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Encrypt {
    boolean value() default true;
}
