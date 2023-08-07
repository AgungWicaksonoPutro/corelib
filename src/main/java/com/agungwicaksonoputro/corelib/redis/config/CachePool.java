package com.agungwicaksonoputro.corelib.redis.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CachePool {
    private Integer maxActive = 20;
    private Integer maxIdle = 10;
    private Integer minIdle = 10;
    private Integer shutdownTimeout = 100;
    private Integer maxWait = 1000;
}
