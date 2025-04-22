package com.shoto.spring.conversion;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * 自定义 {@link PropertyEditorRegistrar}
 * @see PropertyEditorRegistrar
 */
public class CustomizedPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        // 应用 StringToPropertiesPropertyEditor 将传入的 String 值解析成 Properties 存入到 User 的 context 字段中
        registry.registerCustomEditor(User.class, "context", new StringToPropertiesPropertyEditor());
    }
}
