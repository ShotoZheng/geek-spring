package com.shoto.spring.dependency.injection;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于 Spring Java API 实现 setter 方法依赖注入
 */
public class ApiBeanDefinitionDependencySetterInjectionDemo {

    public static void main(String[] args) {
        // 注册 BeanDefinition 信息
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("userHolder", createUserHolderBeanDefinition());

        // 获取 xml 资源配置信息
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String resourcePath = "META-INF/dependency-lookup-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);

        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);

    }

    /**
     * 创建 {@link com.shoto.spring.dependency.injection.UserHolder} 对应 BeanDefinition，并实现依赖注入
     */
    public static BeanDefinition createUserHolderBeanDefinition() {
        BeanDefinitionBuilder userHolderBeanDefinition = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        // 会从 IoC 容器中查找 Bean 名称为 user 的 Bean
        userHolderBeanDefinition.addPropertyReference("user", "superUser");
        return userHolderBeanDefinition.getBeanDefinition();
    }

}
