package com.shoto.spring.dependency.injection;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于 java 注解配置原信息实现 Constructor 依赖注入
 */
public class AnnotationDependencyConstructorInjectionDemo {

    public static void main(String[] args) {

        // 以当前类作为进行注册
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotationDependencyConstructorInjectionDemo.class);

        // 获取 xml 资源配置信息
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(context);
        String resourcePath = "META-INF/dependency-lookup-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);

        context.refresh();

        UserHolder userHolder = context.getBean(UserHolder.class);
        System.out.println(userHolder);

        context.close();
    }


    // @Bean 注解并配合 Constructor 方法实现
    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
