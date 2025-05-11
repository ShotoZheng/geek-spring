package com.shoto.spring.environment;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * {@link PropertySourcesPlaceholderConfigurer } 占位符替换演示
 */
@ImportResource(locations = "classpath:/META-INF/placeholders-resolver.xml")
public class PropertySourcesPlaceholderConfigurerDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(PropertySourcesPlaceholderConfigurerDemo.class);

        context.refresh();

        System.out.println(context.getBean("user", User.class));

        context.close();
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        Resource resource = new ClassPathResource("META-INF/default.properties");
        placeholderConfigurer.setLocation(resource);
        placeholderConfigurer.setFileEncoding("UTF-8");
        return placeholderConfigurer;
    }
}
