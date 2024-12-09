package com.shoto.spring.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 自动模式实现 Constructor 依赖注入
 */
public class AutowiringDependencyConstructorInjectionDemo {
    public static void main(String[] args) {
        // 获取 xml 资源配置信息
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String resourcePath = "META-INF/autowiring-dependency-constructor-injection.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);
    }
}
