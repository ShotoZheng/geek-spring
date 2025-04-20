package com.shoto.spring.validation;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Errors 文案来源
 * @see BeanPropertyBindingResult
 */
public class ErrorMessageDemo {

    public static void main(String[] args) {
        // 错误收集
        User user = new User();
        Errors errors = new BeanPropertyBindingResult(user, "user");
        // 添加全局错误信息
        errors.reject("user.required");
        // 添加字段错误信息
        errors.rejectValue("name", "name.required");

        List<ObjectError> globalErrors = errors.getGlobalErrors();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        List<ObjectError> allErrors = errors.getAllErrors();

        // 打印错误信息文本
        MessageSource messageSource = createMessageSource();
        for (ObjectError error : allErrors) {
            String message = messageSource.getMessage(Objects.requireNonNull(error.getCode()), error.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
        // 打印国际化错误信息文本
        ResourceBundleMessageSource bundleMessageSource = createResourceBundleMessageSource();
        for (ObjectError error : allErrors) {
            String message = bundleMessageSource.getMessage(Objects.requireNonNull(error.getCode()), error.getArguments(), Locale.CHINA);
            System.out.println(message);
            message = bundleMessageSource.getMessage(Objects.requireNonNull(error.getCode()), error.getArguments(), Locale.US);
            System.out.println(message);
        }
    }

    private static ResourceBundleMessageSource createResourceBundleMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    private static MessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("user.required", Locale.getDefault(), "The Object User is required");
        messageSource.addMessage("name.required", Locale.getDefault(), "The Field Name is required");
        return messageSource;
    }
}
