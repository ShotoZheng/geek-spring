package com.shoto.spring.bean.scope;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;
import java.util.stream.IntStream;

/**
 * Bean Scope 演示
 */
public class BeanScopeDemo implements DisposableBean {

    @Bean
    public User singletonUser() {
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User prototypeUser() {
        return createUser();
    }

    private User createUser() {
        User user = new User();
        user.setName(String.valueOf(System.nanoTime()));
        return user;
    }

    @Autowired
    private User singletonUser;

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;

    @Autowired
    private User prototypeUser;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;

    @Autowired
    private Map<String, User> users;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    public static void main(String[] args) {

        // 以当前类作为进行注册
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanScopeDemo.class);

        context.refresh();

        scopedBeansByLookup(context);
        scopedBeansByInjection(context);

        context.close();
    }

    private static void scopedBeansByInjection(AnnotationConfigApplicationContext context) {
        BeanScopeDemo beanScopeDemo = context.getBean(BeanScopeDemo.class);
        System.out.println("singletonUser: " + beanScopeDemo.singletonUser);
        System.out.println("singletonUser1: " + beanScopeDemo.singletonUser1);
        System.out.println("prototypeUser: " + beanScopeDemo.prototypeUser);
        System.out.println("prototypeUser1: " + beanScopeDemo.prototypeUser1);
        System.out.println("prototypeUser2: " + beanScopeDemo.prototypeUser2);
        System.out.println("users: " + beanScopeDemo.users);
    }

    /**
     * 演示依赖查找
     * @param context AnnotationConfigApplicationContext
     */
    private static void scopedBeansByLookup(AnnotationConfigApplicationContext context) {
        IntStream.range(0, 3).forEach(e -> {
            User singletonUser = context.getBean("singletonUser", User.class);
            System.out.println("singletonUser: " + singletonUser);

            User prototypeUser = context.getBean("prototypeUser", User.class);
            System.out.println("prototypeUser: " + prototypeUser);
        });
    }

    /**
     * 当前 Bean 销毁时连带销毁 Prototype Bean
     */
    @Override
    public void destroy() {
        System.out.println("Prototype Bean 开始销毁...");
        this.prototypeUser.destroy();
        this.prototypeUser1.destroy();
        this.prototypeUser2.destroy();
        this.users.forEach((beanName, user) -> {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if (beanDefinition.isPrototype()) {
                user.destroy();
            }
        });
        System.out.println("Prototype Bean 销毁结束...");
    }
}
