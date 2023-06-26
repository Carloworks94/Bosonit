package com.bosonit.block5SpringBoot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationBeans {
    public ConfigurationBeans() {
    }

    @Bean
    Bean1 getBean1() {
        return new Bean1();
    }
}
