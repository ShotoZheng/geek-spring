package com.shoto.spring.i18n;

import org.springframework.context.support.ResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class MessageSourceDemo {
    public static void main(String[] args) {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.toString());
        messageSource.setBasename("i18n/messages");
        String message = messageSource.getMessage("A001", null, Locale.CHINA);
        System.out.println(message);

        message = messageSource.getMessage("A001", null, Locale.US);
        System.out.println(message);

        // 占位符
        message = messageSource.getMessage("A002", new Object[]{"Jane"}, Locale.US);
        System.out.println(message);

        message = messageSource.getMessage("A002", new Object[]{"世界"}, Locale.CHINA);
        System.out.println(message);
    }
}
