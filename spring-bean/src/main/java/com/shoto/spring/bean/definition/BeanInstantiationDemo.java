package com.shoto.spring.bean.definition;

import com.shoto.spring.bean.definition.factory.UserFactoryBean;
import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化示例
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/dependency-instantiation-context.xml");
        // 1. 构造器方式：通过 xml、注解 和 API 方式等元信息
        // 2. 静态方法创建 Bean
        User user = context.getBean("user-by-static-method", User.class);
        System.out.println(user);

        // 3. 工厂创建 Bean
        User userByFactory = context.getBean("user-by-factory", User.class);
        System.out.println(userByFactory);

        // 4. 通过 FactoryBean 创建 Bean
        UserFactoryBean userFactoryBean = context.getBean(UserFactoryBean.class);
        User userByFactoryBean = userFactoryBean.getObject();
        System.out.println(userByFactoryBean);
    }
}
