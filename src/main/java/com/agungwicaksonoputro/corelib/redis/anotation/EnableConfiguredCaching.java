package com.agungwicaksonoputro.corelib.redis.anotation;

import com.agungwicaksonoputro.corelib.redis.config.ConfigProperties;
import com.agungwicaksonoputro.corelib.redis.configuration.CacheManagementConfig;
import com.agungwicaksonoputro.corelib.redis.operation.RedisDataHandler;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotasi kustom {@code @EnableConfiguredCaching} mengaktifkan pengaturan caching terstruktur.
 * Ini mengimpor konfigurasi cache yang telah ditentukan ke dalam aplikasi.
 * Anotasi ini dapat digunakan pada level kelas.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({ConfigProperties.class, CacheManagementConfig.class, RedisDataHandler.class})
public @interface EnableConfiguredCaching {

    /**
     * Anotasi {@code @AliasFor} menghubungkan atribut {@code value} dari anotasi {@code @EnableConfiguredCaching}
     * dengan atribut {@code value} dari anotasi {@code @Import}, sehingga keduanya dapat digunakan secara bersamaan.
     */
    @AliasFor(annotation = Import.class, attribute = "value")
    Class<?>[] value() default {ConfigProperties.class, CacheManagementConfig.class, RedisDataHandler.class};

}
