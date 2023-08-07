package com.agungwicaksonoputro.corelib.redis.operation;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
@Log4j2
public class RedisDataHandler {
    private final JedisPool jedisPool;

    public RedisDataHandler(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    private Jedis getConnection() {
        return jedisPool.getResource();
    }

    public Long delete(String key) {
        try (Jedis jedis = getConnection()) {
            return jedis.del(key);
        }
    }

    public void set(String key, String value, Integer expiration) {
        try (Jedis jedis = getConnection()) {
            jedis.set(key, value);
            if (ObjectUtils.isNotEmpty(expiration)) {
                jedis.expire(key, expiration);
            }
        }
    }

    public String get(String key) {
        try (Jedis jedis = getConnection()) {
            return jedis.get(key);
        }
    }
}
