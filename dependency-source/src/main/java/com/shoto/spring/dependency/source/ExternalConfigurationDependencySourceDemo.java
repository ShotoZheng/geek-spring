package com.shoto.spring.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 外部化配置作为依赖注入来源
 */
@Configuration
@PropertySource(value = "META-INF/default.properties")
public class ExternalConfigurationDependencySourceDemo {

    @Value("${usr.name}")
    private String userName;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ExternalConfigurationDependencySourceDemo.class);

        context.refresh();

        ExternalConfigurationDependencySourceDemo bean = context.getBean(ExternalConfigurationDependencySourceDemo.class);
        System.out.println(bean.userName);

        context.close();
    }
}
