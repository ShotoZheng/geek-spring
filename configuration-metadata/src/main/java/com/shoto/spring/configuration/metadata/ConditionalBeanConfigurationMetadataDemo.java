package com.shoto.spring.configuration.metadata;

import com.shoto.spring.configuration.metadata.conditional.TradeDomainConfig;
import com.shoto.spring.configuration.metadata.conditional.TradeDomainConditionalConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class ConditionalBeanConfigurationMetadataDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationContext =
                new AnnotationConfigApplicationContext();
        annotationContext.register(ConditionalBeanConfigurationMetadataDemo.class);
        annotationContext.register(TradeDomainConditionalConfig.class);
        annotationContext.refresh();
        Map<String, TradeDomainConfig> booksMap = annotationContext.getBeansOfType(TradeDomainConfig.class);
        System.out.println(booksMap);
        annotationContext.close();
    }
}
