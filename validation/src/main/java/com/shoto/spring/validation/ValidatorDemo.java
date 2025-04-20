package com.shoto.spring.validation;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.*;

import java.util.Locale;
import java.util.Objects;

/**
 * 自定义 Spring {@link Validator}
 * @see Validator
 */
public class ValidatorDemo {

    public static void main(String[] args) {
        // 1. 创建 Validator
        Validator validator = new UserValidator();
        User user = new User();
        user.setAge(23); // age 属性不为空
        boolean supports = validator.supports(user.getClass());
        System.out.println("是否支持：" + supports);

        // 2. 模拟创建错误信息
        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);

        // 3. 获取 MessageSource 信息，打印错误信息
        MessageSource messageSource = createMessageSource();
        for (ObjectError error : errors.getAllErrors()) {
            String message = messageSource.getMessage(Objects.requireNonNull(error.getCode()), error.getArguments(), Locale.getDefault());
            System.out.println(message);
        }

    }

    private static MessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("name.required", Locale.getDefault(), "The Field Name is required");
        messageSource.addMessage("age.required", Locale.getDefault(), "The Field Age is required");
        return messageSource;
    }

    static class UserValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            // clazz 是否继承或实现自 User，是则为 true
            return User.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
            ValidationUtils.rejectIfEmpty(errors, "age", "age.required");
            String userName = user.getName();
            // ....
        }
    }
}
