package com.agungwicaksonoputro.corelib.redis.configuration;

import com.agungwicaksonoputro.corelib.redis.config.ConfigProperties;
import com.agungwicaksonoputro.corelib.redis.handler.CacheErrorLogger;
import com.agungwicaksonoputro.corelib.redis.operation.RedisDataHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Konfigurasi manajemen cache untuk aplikasi.
 */
@EnableCaching
@Configuration
@ConditionalOnProperty(name = "cache.enabled", havingValue = "true", matchIfMissing = true)
public class CacheManagementConfig extends CachingConfigurerSupport implements CachingConfigurer {

    /**
     * Mengkonfigurasi JedisPool yang digunakan untuk interaksi dengan server Redis.
     */
    @Bean
    public JedisPool jedisPool(ConfigProperties configProperties) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(configProperties.getPool().getMaxActive());
        poolConfig.setMaxIdle(configProperties.getPool().getMaxIdle());
        poolConfig.setBlockWhenExhausted(true);
        poolConfig.setMaxWaitMillis(configProperties.getPool().getMaxWait());
        poolConfig.setMinIdle(configProperties.getPool().getMinIdle());
        poolConfig.setEvictorShutdownTimeoutMillis(configProperties.getPool().getShutdownTimeout());

        return new JedisPool(poolConfig,
                configProperties.getHost(),
                configProperties.getPort(),
                configProperties.getCommandTimeout(),
                configProperties.getPassword(),
                configProperties.getDbIndex());
    }

    /**
     * Mengkonfigurasi penanganan kesalahan yang terjadi pada cache.
     */
    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorLogger();
    }

    /**
     * Membuat instance RedisDataHandler yang akan digunakan untuk operasi cache.
     */
    @Bean
    public RedisDataHandler cacheOperation(JedisPool jedisPool) {
        return new RedisDataHandler(jedisPool);
    }
}
