package com.shoto.spring.environment;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * 依赖查找 {@link Environment} 示例
 */
public class LookupEnvironmentDemo implements EnvironmentAware{

    private Environment environment;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(LookupEnvironmentDemo.class);

        context.refresh();

        LookupEnvironmentDemo environmentDemo = context.getBean(LookupEnvironmentDemo.class);
        System.out.println(environmentDemo.environment);

        // 依赖查找1
        Environment environment1 = context.getBean(ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME, Environment.class);
        System.out.println(environment1 == environmentDemo.environment);

        // 依赖查找2
        ConfigurableEnvironment environment2 = context.getEnvironment();
        System.out.println(environment2 == environmentDemo.environment);

        context.close();

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
