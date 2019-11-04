package com.fh.config;

import com.fh.domain.Student;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

// 创建配置类
@Configuration
public class RedisConfig {

    // 注册到容器中
    @Bean
    public RedisTemplate<Object, Student> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Student> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // 创建json序列化
        Jackson2JsonRedisSerializer<Student> serializer =
                new Jackson2JsonRedisSerializer<Student>(Student.class);
        // 指定序列化为json
        template.setDefaultSerializer(serializer);
        return template;
    }

//    // 使用自定义的CacheManager
//    public RedisCacheManager studentManager(RedisTemplate<Object,Student> studentRedisTemplate){
//        RedisCacheManager redisCacheManager = new RedisCacheManager(studentRedisTemplate);
//        redisCacheManager.setTransactionAware(true);
//        return redisCacheManager;
//    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        //设置CacheManager的值序列化方式为json序列化
        RedisSerializer<Object> jsonSerializer = new GenericJackson2JsonRedisSerializer();
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(jsonSerializer);
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(pair);
        //设置默认超过期时间是30秒
        defaultCacheConfig.entryTtl(Duration.ofSeconds(30));
        //初始化RedisCacheManager
        return new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
    }
}
