package com.shoto.spring.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
@MyConfiguration(name = "hello-world-application")
public class HelloWorldConfiguration {

    @Bean
    public String helloWorld() {
        return "Hello World!";
    }

}
