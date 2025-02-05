package com.shoto.spring.bean.life.cycle;

import com.shoto.spring.ioc.overview.domain.SuperUser;
import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

public class DestructionBeanPostProcessorDemo {

    public static void main(String[] args) {
        // 以当前类作为进行注册
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 获取 xml 资源配置信息
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String resourcePath = "META-INF/dependency-lookup-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);

        beanFactory.addBeanPostProcessor((DestructionAwareBeanPostProcessor) (bean, beanName) -> {
            if (ObjectUtils.nullSafeEquals(beanName, "superUser") && SuperUser.class.equals(bean.getClass())) {
                SuperUser superUser = (SuperUser) bean;
                superUser.setAddress("SHANWEI");
            }
        });
        // 查找 bean
        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);

        // 触发销毁
        beanFactory.destroyBean("superUser", superUser);
        System.out.println(beanFactory.getBean(User.class));
    }
}
