package com.shoto.spring.data.binding;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link DataBinder} 使用示例
 */
public class DataBinderDemo {
    public static void main(String[] args) {
        User user = new User();
        DataBinder dataBinder = new DataBinder(user, "user");

        Map<String, Object> source = new HashMap<>();
        source.put("name", "tawson");
        source.put("age", 23);
        // 未知属性
        source.put("address", "china");

        // 级联属性
        source.put("company.name", "none");
        PropertyValues propertyValues = new MutablePropertyValues(source);

        // 未知属性报错
//        dataBinder.setIgnoreUnknownFields(false);

        // 级联属性不支持
//        dataBinder.setAutoGrowNestedPaths(false);

        // 设置必传字段，不报错，从 BindingResult 获取
        dataBinder.setRequiredFields("city");

        dataBinder.bind(propertyValues);

        BindingResult bindingResult = dataBinder.getBindingResult();
        System.out.println(bindingResult);

        System.out.println(user);
    }
}
