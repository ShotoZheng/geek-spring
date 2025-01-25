package com.shoto.spring.bean.life.cycle;

import com.shoto.spring.ioc.overview.domain.SuperUser;
import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

public class InitializationBeanPostProcessorDemo {

    public static void main(String[] args) {
        // 以当前类作为进行注册
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof SuperUser superUser && ObjectUtils.nullSafeEquals(beanName, "superUser")) {
                    superUser.setAddress("SHENZHEN");
                }
                return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof SuperUser superUser && ObjectUtils.nullSafeEquals(beanName, "superUser")) {
                    superUser.setAddress("GUANGDONG");
                    return bean;
                }
                return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
            }
        });

        // 获取 xml 资源配置信息
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String resourcePath = "META-INF/dependency-lookup-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);

        System.out.println(beanFactory.getBean(User.class));
    }
}
