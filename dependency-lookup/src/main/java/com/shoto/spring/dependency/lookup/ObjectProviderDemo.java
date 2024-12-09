package com.shoto.spring.dependency.lookup;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Bean 延迟依赖查找
 */
public class ObjectProviderDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ObjectProviderDemo.class);
        context.refresh();
        lookupByObjectProvider(context);
        context.close();
    }


    private static void lookupByObjectProvider(AnnotationConfigApplicationContext context) {
        ObjectProvider<User> beanProvider = context.getBeanProvider(User.class);
        User user = beanProvider.getObject();
        System.out.println(user);

        // 函数式接口扩展
        User userBean = beanProvider.getIfAvailable(User::createUser);
        System.out.println(userBean);
    }

    @Bean
    public User user() {
        User user = new User();
        user.setName("shoto");
        user.setAge(25);
        return user;
    }
}
