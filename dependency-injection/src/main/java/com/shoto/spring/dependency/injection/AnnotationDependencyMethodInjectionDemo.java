package com.shoto.spring.dependency.injection;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 实现 IoC 方法注入示例
 */
public class AnnotationDependencyMethodInjectionDemo {

    private User user;

    @Autowired
    public void init(User user) {
        this.user = user;
    }

    public static void main(String[] args) {
        // 以当前类作为进行注册
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotationDependencyMethodInjectionDemo.class);

        // 获取 xml 资源配置信息
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(context);
        String resourcePath = "META-INF/dependency-lookup-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);

        context.refresh();

        AnnotationDependencyMethodInjectionDemo demo = context.getBean(AnnotationDependencyMethodInjectionDemo.class);
        System.out.println(demo.user);

        context.close();
    }
}
