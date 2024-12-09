package com.shoto.spring.bean.definition;

import com.shoto.spring.bean.definition.factory.DefaultUserFactory;
import com.shoto.spring.bean.definition.factory.UserFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * bean 初始化示例
 */
public class BeanInitializationDemo {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanInitializationDemo.class);
        context.refresh();
        System.out.println("容器已启动");
        UserFactory userFactory = context.getBean(UserFactory.class);

        System.out.println("容器准备关闭");
        context.close();
        System.out.println("容器已关闭");
        // 手动 GC
//        Thread.sleep(10000);
//        System.gc();
//        Thread.sleep(10000);
    }

    // 延迟初始化 Bean
    @Lazy(value = true)
    @Bean(initMethod = "initUserFactory", destroyMethod = "destroyUserFactory")
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
