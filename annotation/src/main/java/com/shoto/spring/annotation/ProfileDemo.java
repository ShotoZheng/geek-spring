package com.shoto.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * {@link Profile} 注解使用示例
 * @see Profile
 * @see Conditional
 */
public class ProfileDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ProfileDemo.class);

        ConfigurableEnvironment environment = context.getEnvironment();
        environment.setDefaultProfiles("odd"); // 设置默认 profile
//        environment.setActiveProfiles("even"); // 设置激活 profile
        // 采用 -Dspring.profiles.active=even 实现
        context.refresh();

        Integer number = context.getBean("number", Integer.class);
        System.out.println(number);

        context.close();
    }

    @Bean("number")
    @Profile("odd")
    public Integer odd() {
        return 1;
    }

    @Bean("number")
//    @Profile("even")
    @Conditional(EvenCondition.class)
    public Integer even() {
        return 2;
    }
}
