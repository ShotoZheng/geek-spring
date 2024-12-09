package com.shoto.spring.ioc.overview.dependency.injection;

import com.shoto.spring.ioc.overview.repository.UserRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author admin
 */
public class DependencyInjectionDemo {


    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");
        // 获取 bean 集合
        UserRepository userRepository = context.getBean(UserRepository.class);
        System.out.println(userRepository.getUsers());

        // 获取自定义 bean
        System.out.println(userRepository.getUser());

        // 获取内建依赖
        System.out.println(userRepository.getBeanFactory());

        // 获取内建 bean
//        Environment environment = context.getEnvironment();
        Environment environment = context.getBean(Environment.class);
        System.out.println(environment);
    }

}
