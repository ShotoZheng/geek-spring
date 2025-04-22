package com.shoto.spring.conversion;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * 模拟自定义转换器，String -> Properties
 * @see PropertyEditorSupport
 */
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Properties props = new Properties();
        try {
            props.load(new StringReader(text));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setValue(props);
    }

    @Override
    public String getAsText() {
        Properties props = (Properties) getValue();
        return props.entrySet().stream()
                .map(e -> e.getKey().toString().concat("=").concat(e.getValue().toString())).collect(Collectors.joining());
    }
}
