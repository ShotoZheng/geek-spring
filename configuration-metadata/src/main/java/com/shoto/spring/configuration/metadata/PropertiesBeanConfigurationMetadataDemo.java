package com.shoto.spring.configuration.metadata;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.nio.charset.StandardCharsets;

public class PropertiesBeanConfigurationMetadataDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
        Resource resource = new ClassPathResource("/META-INF/user-bean-config.properties");
        EncodedResource encodedResource = new EncodedResource(resource, StandardCharsets.UTF_8);
        reader.loadBeanDefinitions(encodedResource);

        User user = beanFactory.getBean(User.class);

        System.out.println(user);
    }
}
