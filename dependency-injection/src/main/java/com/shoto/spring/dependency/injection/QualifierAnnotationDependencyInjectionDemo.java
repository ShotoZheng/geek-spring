package com.shoto.spring.dependency.injection;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * {@link  Qualifier } 实现依赖注入示例
 */
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    @Qualifier("user")
    private User user;

    @Autowired
    private User superUser;

    @Autowired
    @Qualifier // 使用 Qualifier 实现分组
    private List<User> qualifierUser;

    @Bean
    @Qualifier
    private User user1() {
        return createUser("zhangsan");
    }

    @Bean
    @Qualifier
    private User user2() {
        return createUser("lisi");
    }

    private static User createUser(String name) {
        User user = new User();
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {

        // 以当前类作为进行注册
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(QualifierAnnotationDependencyInjectionDemo.class);

        // 获取 xml 资源配置信息
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(context);
        String resourcePath = "META-INF/dependency-lookup-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);

        context.refresh();

        QualifierAnnotationDependencyInjectionDemo demo = context.getBean(QualifierAnnotationDependencyInjectionDemo.class);
        System.out.println(demo.user);
        System.out.println(demo.superUser);
        System.out.println(demo.qualifierUser); // zhangsan + lisi

        context.close();
    }
}
