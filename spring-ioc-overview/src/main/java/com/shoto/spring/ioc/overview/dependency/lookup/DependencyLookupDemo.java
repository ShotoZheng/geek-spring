package com.shoto.spring.ioc.overview.dependency.lookup;

import com.shoto.spring.ioc.overview.annotation.Super;
import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @author admin
 */
public class DependencyLookupDemo {


    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");
        lookupById(context);
//        lookupByNamed(context);
//        lookupByType(context);
//        lookupByLazy(context);
//        lookupCollectionByType(context);
//        lookupByAnnotation(context);
    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Object> userBeansMap = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("注解获取：" + userBeansMap);
        }
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userBeansMap = listableBeanFactory.getBeansOfType(User.class);
            System.out.println(userBeansMap);
        }
    }

    private static void lookupByLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟加载：" + user);
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }

    private static void lookupById(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println(user);
    }

    private static void lookupByNamed(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println(user);
    }
}
