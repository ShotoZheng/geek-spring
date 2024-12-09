package com.shoto.spring.dependency.lookup;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(TypeSafetyDependencyLookupDemo.class);
        // 启动应用上下文
        context.refresh();
        // 非安全性类型查找（找不到 Bean 会抛异常）
        printBeansTrace("lookupByGetBean", () -> lookupByGetBean(context));
        printBeansTrace("lookupByObjectFactory", () -> lookupByObjectFactory(context));
        // 安全性类型查找
        printBeansTrace("lookupByGetIfAvailable", () -> lookupByGetIfAvailable(context));
        printBeansTrace("lookupByGetBeansByOfType", () -> lookupByGetBeansByOfType(context));
        printBeansTrace("lookupByGetStreamOps", () -> lookupByGetStreamOps(context));
        context.close();
    }

    private static void lookupByGetStreamOps(AnnotationConfigApplicationContext context) {
        context.getBeanProvider(User.class).forEach(System.out::println);
    }

    private static void lookupByGetBeansByOfType(AnnotationConfigApplicationContext context) {
        context.getBeansOfType(User.class);
    }

    private static void lookupByGetIfAvailable(AnnotationConfigApplicationContext context) {
        ObjectProvider<User> beanProvider = context.getBeanProvider(User.class);
        beanProvider.getIfAvailable();
    }

    private static void lookupByObjectFactory(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = beanFactory.getBeanProvider(User.class);
        objectFactory.getObject();
    }

    private static void lookupByGetBean(BeanFactory beanFactory) {
        beanFactory.getBean(User.class);
    }

    private static void printBeansTrace(String source, Runnable runnable) {
        System.err.println("=================================<<" + source + ">>=================================");
        try {
            runnable.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
