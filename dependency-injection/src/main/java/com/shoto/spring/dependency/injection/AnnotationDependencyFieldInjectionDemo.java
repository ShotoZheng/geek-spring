package com.shoto.spring.dependency.injection;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 实现 IoC 属性注入示例
 */
public class AnnotationDependencyFieldInjectionDemo {

    @Autowired
    private User user;

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

        context.close();
    }
}
