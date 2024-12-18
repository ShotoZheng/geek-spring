package com.shoto.spring.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * 演示非 Spring 容器托管对象
 */
public class ResolvableDependencyAsSourceDemo {

    @Autowired
    private ResolvableDependencyUser user;

    @PostConstruct
    public void init() {
        System.out.println(user);
    }

    public static void main(String[] args) {
        // 以当前类作为进行注册
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ResolvableDependencyAsSourceDemo.class);
        context.addBeanFactoryPostProcessor(beanFactory -> beanFactory.registerResolvableDependency(ResolvableDependencyUser.class,
                new ResolvableDependencyUser("lisi")));
        context.refresh();

        context.close();
    }

    public static class ResolvableDependencyUser {
        private String username;

        public ResolvableDependencyUser() {
        }

        public ResolvableDependencyUser(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String toString() {
            return "ResolvableDependencyUser{" +
                    "username='" + username + '\'' +
                    '}';
        }
    }
}
