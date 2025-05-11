package com.shoto.spring.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * 依赖注入 {@link org.springframework.core.env.Environment} 示例
 */
public class InjectingEnvironmentDemo implements EnvironmentAware, ApplicationContextAware {

    private Environment environment;

    @Autowired
    private Environment autowiredEnvironment;

    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(InjectingEnvironmentDemo.class);

        context.refresh();

        InjectingEnvironmentDemo environmentDemo = context.getBean(InjectingEnvironmentDemo.class);
        System.out.println(environmentDemo.environment);

        ConfigurableEnvironment contextEnvironment = context.getEnvironment();
        System.out.println(environmentDemo.environment == contextEnvironment);

        System.out.println(environmentDemo.environment == environmentDemo.autowiredEnvironment);

        System.out.println(environmentDemo.applicationContext.getEnvironment() == environmentDemo.environment);

        context.close();

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
