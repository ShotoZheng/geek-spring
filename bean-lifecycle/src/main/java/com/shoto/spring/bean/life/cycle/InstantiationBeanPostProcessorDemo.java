package com.shoto.spring.bean.life.cycle;

import com.shoto.spring.ioc.overview.domain.SuperUser;
import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

public class InstantiationBeanPostProcessorDemo {

    public static void main(String[] args) {
        // 以当前类作为进行注册
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.addBeanPostProcessor(new InstantiationAwareBeanPostProcessor() {
            // 实例化前阶段
            @Override
            public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
                return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
            }

            // 实例化后阶段，false 则不进行属性赋值
            @Override
            public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
                return true;
            }

            // 对属性进行赋值
            @Override
            public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
                if (bean instanceof SuperUser && ObjectUtils.nullSafeEquals(beanName, "superUser")) {
                    PropertyValue address = pvs.getPropertyValue("address");
                    if (Objects.nonNull(address)) {
                        ((MutablePropertyValues) pvs).removePropertyValue("address");
                        ((MutablePropertyValues) pvs).add("address", "GUANGZHOU");
                    }
                }
                return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
            }
        });

        // 获取 xml 资源配置信息
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String resourcePath = "META-INF/dependency-lookup-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);

        System.out.println(beanFactory.getBean(User.class));

    }
}
