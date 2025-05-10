package com.shoto.spring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(HelloWorldConfiguration.class) // 方法一，导入配置类
//@Import(HelloWorldImportSelector.class) // 方法二，导入 ImportSelector 类
@Import(HelloWorldBeanDefinitionRegistrar.class) // 方法三，导入 BeanDefinitionRegistrar 类
public @interface EnableHelloWorld {
}
