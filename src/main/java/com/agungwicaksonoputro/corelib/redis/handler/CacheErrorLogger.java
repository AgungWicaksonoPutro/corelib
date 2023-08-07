package com.agungwicaksonoputro.corelib.redis.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@Log4j2
public class CacheErrorLogger implements CacheErrorHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        log.error("Unable to get from cache [{}] : {}", cache.getName(), exception.getMessage());
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, @Nullable Object value) {
        log.error("Unable to put into cache [{}] : {}", cache.getName(), exception.getMessage());
    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        log.error("Unable to evict from cache [{}] : {}", cache.getName(), exception.getMessage());
    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        log.error("Unable to clean cache [{}] : {}", cache.getName(), exception.getMessage());
    }

    public <T> List<T> readList(String cacheContent, Class<T> clazz) {
        try {
            CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class, clazz);
            return objectMapper.readValue(cacheContent, typeReference);
        } catch (Exception ex) {
            log.error("Error occurred when reading list from cache: {}", ex.getMessage());
        }
        return Collections.emptyList();
    }

    public <T> Optional<T> readOptional(String cacheContent, Class<T> clazz) {
        try {
            return Optional.ofNullable(objectMapper.readValue(cacheContent, clazz));
        } catch (JsonProcessingException ex) {
            log.error("Error occurred when reading optional from cache: {}", ex.getMessage());
        }
        return Optional.empty();
    }
}
