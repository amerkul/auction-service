package com.example.auction.config;

import com.example.auction.kafka.dto.AccountCreatedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;
    @Value("${spring.data.redis.port}")
    private Integer redisPort;

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfig = new RedisStandaloneConfiguration();
        redisStandaloneConfig.setHostName(this.redisHost);
        redisStandaloneConfig.setPort(this.redisPort);
        return new LettuceConnectionFactory(redisStandaloneConfig);
    }

    @Bean
    public ReactiveRedisOperations<String, AccountCreatedEvent> redisOperations(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        Jackson2JsonRedisSerializer<AccountCreatedEvent> serializer = new Jackson2JsonRedisSerializer<>(AccountCreatedEvent.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, AccountCreatedEvent> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
        RedisSerializationContext<String, AccountCreatedEvent> context = builder.value(serializer)
                .hashValue(serializer)
                .hashKey(serializer)
                .build();
        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, context);
    }

}
