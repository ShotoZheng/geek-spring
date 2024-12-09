package com.shoto.spring.dependency.injection;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 实现 IoC 接口回调注入示例
 */
public class AwareAnnotationDependencyInjectionDemo implements BeanFactoryAware, ApplicationContextAware {

    private User user;

    private BeanFactory beanFactory;

    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        // 以当前类作为进行注册
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AwareAnnotationDependencyInjectionDemo.class);

        // 获取 xml 资源配置信息
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(context);
        String resourcePath = "META-INF/dependency-lookup-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);

        context.refresh();

        AwareAnnotationDependencyInjectionDemo demo = context.getBean(AwareAnnotationDependencyInjectionDemo.class);
        System.out.println(demo.user);
        System.out.println(demo.beanFactory);
        System.out.println(demo.applicationContext);

        context.close();
    }

    // 基于接口注入 BeanFactory
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        this.user = beanFactory.getBean(User.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
