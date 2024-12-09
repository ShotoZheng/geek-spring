package com.shoto.spring.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * XML 资源配置元信息实现 Setter 方法依赖注入
 */
public class XmlDependencySetterInjectionDemo {

    public static void main(String[] args) {
        // 获取 xml 资源配置信息
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String resourcePath = "META-INF/dependency-setter-injection.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);
    }
}
