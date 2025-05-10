package com.shoto.spring.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MyComponent {

    /**
     * 利用 Component 注解属性 value 作为 MyComponent 注解属性 name 的别名
     */
    @AliasFor(annotation = Component.class, attribute = "value")
    String name() default "";
}
