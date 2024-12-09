package com.shoto.spring.bean.definition;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

// @Import 实现将 BeanDefinition 添加到 IoC 容器中
//@Import(BeanDefinitionRegisterDemo.AppConfig.class)
public class BeanDefinitionRegistrationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        registerByImport(context);
//        registerByBean(context);
//        registerByComponent(context);
        registerBeanDefinition(context, "user");
        registerBeanDefinition(context, null);
        context.refresh();
//        System.out.println(context.getBean(AppConfig.class));
        Map<String, User> userBeans = context.getBeansOfType(User.class);
        System.out.println(userBeans);
        context.close();
    }

    private static void registerByGeneratedName(AnnotationConfigApplicationContext context) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "郑松涛")
                .addPropertyValue("age", 25);
        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), context);
    }

    private static void registerBeanDefinition(AnnotationConfigApplicationContext context, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "郑松涛")
                .addPropertyValue("age", 25);
        if (StringUtils.isEmpty(beanName)) {
            registerByGeneratedName(context);
        } else {
            context.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        }
    }

    private static void registerByBean(AnnotationConfigApplicationContext context) {
        context.register(AppConfig.class);
    }

    private static void registerByImport(AnnotationConfigApplicationContext context) {
        context.register(BeanDefinitionRegistrationDemo.class);
    }

    public static void registerByComponent(AnnotationConfigApplicationContext context) {
        context.register(AppConfig.class);
    }

    // @Component 实现 BeanDefinition 注册到 IoC 容器
    @Component
    public static class AppConfig {
//        @Bean
//        public User user() {
//            return new User();
//        }
    }

}
