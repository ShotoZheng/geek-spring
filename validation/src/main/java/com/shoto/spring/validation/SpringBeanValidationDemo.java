package com.shoto.spring.validation;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @see LocalValidatorFactoryBean
 * @see MethodValidationPostProcessor
 */
public class SpringBeanValidationDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/validation-context.xml");
        // 校验是否开启校验
        Validator validator = context.getBean(Validator.class);
        System.out.println(validator instanceof LocalValidatorFactoryBean);

        // 执行校验
        UserProcessor userProcessor = context.getBean(UserProcessor.class);
        User user = new User();
//        user.setName("tawson");
        userProcessor.process(user);

        context.close();
    }

    // @Validated 和 @Valid 的生效依赖 MethodValidationPostProcessor
    // MethodValidationPostProcessor：用于启用方法级别的校验，它会在方法process调用前对参数进行校验。
    @Validated
    @Component
    static class UserProcessor {
        public void process(@Valid User user) {
            System.out.println(user);
        }
    }


    static class User {
        @NotNull(message = "name must not be null")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
