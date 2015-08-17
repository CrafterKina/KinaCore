package com.mods.kina.KinaCore.movelib.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface KinaProp{
    boolean isInstance() default false;

    String category() default "";

    String name() default "";

    String comment() default "";
}
