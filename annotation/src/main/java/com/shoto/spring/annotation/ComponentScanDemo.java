package com.shoto.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * {@link ComponentScan } 注解示例
 */
//@ComponentScan(value = "com.shoto.spring.annotation")
@ComponentScan(basePackages = "com.shoto.spring.annotation") // 注解别名
public class ComponentScanDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ComponentScanDemo.class);

        context.refresh();

        String helloWorld = (String) context.getBean("helloWorld");
        System.out.println(helloWorld);

        Object testClass = context.getBean("testClass");
        System.out.println(testClass);

        context.close();

    }
}
