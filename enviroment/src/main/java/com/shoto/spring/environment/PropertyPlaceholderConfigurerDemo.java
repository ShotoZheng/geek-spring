package com.shoto.spring.environment;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Environment 占位符示例
 * {@link PropertyPlaceholderConfigurer } 占位符替换演示
 *
 */
@ImportResource(locations = "classpath:/META-INF/placeholders-resolver.xml")
public class PropertyPlaceholderConfigurerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(PropertyPlaceholderConfigurerDemo.class);

        context.refresh();

        System.out.println(context.getBean("user", User.class));

        context.close();
    }

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer placeholderConfigurer = new PropertyPlaceholderConfigurer();
        Resource resource = new ClassPathResource("META-INF/default.properties");
        placeholderConfigurer.setLocation(resource);
        placeholderConfigurer.setFileEncoding("UTF-8");
        return placeholderConfigurer;
    }
}
