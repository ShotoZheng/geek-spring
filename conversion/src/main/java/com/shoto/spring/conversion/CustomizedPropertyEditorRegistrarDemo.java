package com.shoto.spring.conversion;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * {@link CustomizedPropertyEditorRegistrar} 使用演示
 */
public class CustomizedPropertyEditorRegistrarDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/properties-edit-conversion-context.xml");

        User user = context.getBean(User.class);
        System.out.println(user);
        context.close();
    }
}
