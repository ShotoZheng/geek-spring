package com.shoto.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link org.springframework.beans.factory.annotation.Value} 演示示例
 */
public class ValueInjectionDemo {

    @Value("${user.name}")
    private String userName;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ValueInjectionDemo.class);

        // 重置属性值
        MutablePropertySources propertySources = context.getEnvironment().getPropertySources();
        Map<String, Object> map = new HashMap<>();
        map.put("user.name", "郑松涛");
        MapPropertySource propertySource = new MapPropertySource("properties-sources", map);
        propertySources.addFirst(propertySource);

        context.refresh();

        // 会获取本地操作系统机器名称
        ValueInjectionDemo valueInjectionDemo = context.getBean(ValueInjectionDemo.class);
        System.out.println(valueInjectionDemo.userName);

        propertySources.forEach(e -> System.out.println(e.getName() + ":" + e.getProperty("user.name")));

        context.close();

    }
}
