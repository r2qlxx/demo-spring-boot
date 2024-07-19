package com.example.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class LogConfig {

    @Bean
    public MessageSource appLog() {
        ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
        rbms.addBasenames("applog");
        rbms.setDefaultEncoding("UTF-8");
        return rbms;
    }
}
