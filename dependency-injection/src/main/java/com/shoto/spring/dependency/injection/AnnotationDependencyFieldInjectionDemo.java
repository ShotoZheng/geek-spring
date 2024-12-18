package com.shoto.spring.dependency.injection;

import com.shoto.spring.dependency.injection.annotation.AutowiredUser;
import com.shoto.spring.dependency.injection.annotation.InjectUser;
import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 实现 IoC 属性注入示例
 */
public class AnnotationDependencyFieldInjectionDemo {

    @Autowired
    private User user;

    /**
     * 自定义注解注入
     */
    @InjectUser
    private User injectUser;

    /**
     * 自定义注解注入
     */
    @AutowiredUser
    private User autowiredUser;

    @Bean
//    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor autowiredUserAnnotationBeanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(AutowiredUser.class);
        return beanPostProcessor;
    }

    public static void main(String[] args) {

        // 以当前类作为进行注册
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotationDependencyFieldInjectionDemo.class);

        // 获取 xml 资源配置信息
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(context);
        String resourcePath = "META-INF/dependency-lookup-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);

        context.refresh();

        AnnotationDependencyFieldInjectionDemo demo = context.getBean(AnnotationDependencyFieldInjectionDemo.class);
        System.out.println(demo.user);
        System.out.println(demo.injectUser);
        System.out.println(demo.autowiredUser);

        context.close();
    }
}
