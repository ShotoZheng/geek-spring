package com.shoto.spring.configuration.metadata;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

@Import(User.class)
@ImportResource("classpath:/META-INF/dependency-injection-context.xml")
@PropertySource("classpath:/META-INF/user-bean-config.properties")
//@PropertySource("classpath:/META-INF/user-bean-config.properties")
// 因为@Repeatable(PropertySources.class)所以可叠加多个@PropertySource
public class AnnotatedSpringIoCContainerMetadataConfigurationDemo {

    @Bean("tawson")
    public User createUser(@Value("${user.name}") String username, @Value("${user.age}") Integer age) {
        User user = new User();
        user.setName(username);
        user.setAge(age);
        return user;
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotatedSpringIoCContainerMetadataConfigurationDemo.class);
        context.refresh();

//        User user = context.getBean(User.class);
//        System.out.println(user);

        Map<String, User> userMap = context.getBeansOfType(User.class);
        userMap.forEach((beanName, user) -> System.out.println("beanName: " + beanName + " user: " + user));

        context.close();
    }
}
