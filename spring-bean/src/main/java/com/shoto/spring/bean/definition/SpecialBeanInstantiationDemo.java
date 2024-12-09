package com.shoto.spring.bean.definition;

import com.shoto.spring.bean.definition.factory.DefaultUserFactory;
import com.shoto.spring.bean.definition.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ServiceLoader;

/**
 * 特殊的 Bean 实例化方式
 */
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {
        // 1. 通过 ServiceLoader 进行 Bean 实例化
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/dependency-instantiation-context.xml");
        ServiceLoader<UserFactory> serviceLoader = context.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoader(serviceLoader);
//        instantiationByServiceLoader();
        // 2. 通过 AutowireCapableBeanFactory 进行 Bean 实例化
        AutowireCapableBeanFactory autowireCapableBeanFactory = context.getAutowireCapableBeanFactory();
        UserFactory userFactory = autowireCapableBeanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser());
    }

    public static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        serviceLoader.forEach(SpecialBeanInstantiationDemo::printCreateUser);
    }

    public static void instantiationByServiceLoader() {
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        serviceLoader.forEach(SpecialBeanInstantiationDemo::printCreateUser);
    }

    private static void printCreateUser(UserFactory userFactory) {
        System.out.println(userFactory.createUser());
    }
}
