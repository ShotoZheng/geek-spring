package com.shoto.spring.configuration.metadata.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;


public class TradeDomainConditionalConfig {

    @Conditional(TradeDomainConditional.class)
    @Bean
    public TradeDomainConfig tradeDomainConfig() {
        return new TradeDomainConfig("https://localhost");
    }

    @Conditional(OutTradeDomainConditional.class)
    @Bean
    public TradeDomainConfig outTradeDomainConfig() {
        return new TradeDomainConfig("https://192.168.1.1");
    }
}
