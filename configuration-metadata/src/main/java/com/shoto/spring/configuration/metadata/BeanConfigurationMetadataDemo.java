package com.shoto.spring.configuration.metadata;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.*;
import org.springframework.util.ObjectUtils;

public class BeanConfigurationMetadataDemo {

    public static void main(String[] args) {
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue("name", "tawson");
        genericBeanDefinition.setPropertyValues(propertyValues);
        genericBeanDefinition.setBeanClass(User.class);

        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
        rootBeanDefinition.setPropertyValues(propertyValues);
        rootBeanDefinition.setBeanClass(User.class);


        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "tawson zheng");
        AbstractBeanDefinition definitionBuilderBeanDefinition = beanDefinitionBuilder.getBeanDefinition();
        definitionBuilderBeanDefinition.setAttribute("name", "shoto");

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("user", genericBeanDefinition);
        beanFactory.registerBeanDefinition("user1", rootBeanDefinition);
        beanFactory.registerBeanDefinition("user2", definitionBuilderBeanDefinition);
        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals("user2", beanName) && bean instanceof User user) {
                    // 获取上下文
                    BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                    String userName = (String) beanDefinition.getAttribute("name");
                    user.setName(userName);
                    System.out.println(user);
                }
                return bean;
            }
        });

        User user = beanFactory.getBean("user", User.class);
        User user1 = beanFactory.getBean("user1", User.class);
        User user2 = beanFactory.getBean("user2", User.class);
        System.out.println("user: " + user);
        System.out.println("user1: " + user1);
        System.out.println("user2: " + user2);
    }
}
