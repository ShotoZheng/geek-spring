package com.shoto.spring.ioc.overview.container;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ApplicationContextAsIoCContainerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApplicationContextAsIoCContainerDemo.class);
        // 启动容器
        context.refresh();
        lookupCollectionByType(context);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userBeansMap = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("ApplicationContext 创建 Bean: " + userBeansMap);
        }
    }

    @Bean
    public User user() {
        User user = new User();
        user.setName("shoto");
        user.setAge(25);
        return user;
    }
}
