package com.shoto.spring.annotation;


import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent
public @interface MyComponent2 {
    // 属性覆盖，覆盖了 @MyComponent 的 name 属性
    String name() default "";
}
