package com.agungwicaksonoputro.corelib.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "redis")
public class ConfigProperties {
    private String host;
    private Integer port;
    private String password;
    private Integer commandTimeout;
    private Integer expireSeconds;
    private Integer dbIndex = 0;
    private CachePool pool;
}
