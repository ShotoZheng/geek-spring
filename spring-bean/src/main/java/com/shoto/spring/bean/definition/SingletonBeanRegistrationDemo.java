package com.shoto.spring.bean.definition;

import com.shoto.spring.bean.definition.factory.DefaultUserFactory;
import com.shoto.spring.bean.definition.factory.UserFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 外部单体兑现实现 BeanDefinition 注册
 */
public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {
        UserFactory userFactory = new DefaultUserFactory();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
        beanFactory.registerSingleton("userFactory", userFactory);
        context.refresh();
        // 依赖查找
        UserFactory userFactoryByLookup = beanFactory.getBean(UserFactory.class);
        System.out.println(userFactoryByLookup);
        System.out.println(userFactoryByLookup == userFactory);
        context.close();
    }
}
