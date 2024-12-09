package com.shoto.spring.bean.definition;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * BeanDefinition 创建示例
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {
        // 通过 Builder 生成普通 BeanDefinition
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "郑松涛")
                .addPropertyValue("age", 25);
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        System.out.println(beanDefinition);

        // 通过抽象类 AbstractBeanDefinition 实现
        AnnotatedGenericBeanDefinition annotatedGenericBeanDefinition = new AnnotatedGenericBeanDefinition(User.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues()
                .add("name", "张三")
                .add("age", 25);
        annotatedGenericBeanDefinition.setPropertyValues(propertyValues);
        System.out.println(annotatedGenericBeanDefinition);
    }
}
