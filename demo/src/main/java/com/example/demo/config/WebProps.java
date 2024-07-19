package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "web")
@Getter
@Setter
public class WebProps {
    private int connectionRequestTimeout;
    private int connectTimeout;
    private int responseTimeout;
    private int maxTotal;
    private int defaultMaxPerRoute;
    private String protocol;
    private String host;
    private String port;
    private String path;
}
