package com.example.auction.redis;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class RedisService {

    private final ReactiveRedisOperations<String, Object> redisOperations;

    /**
     * Set key and value into a hash key
     * @param key key value - must not be null.
     * @param hashKey hash key value -  must not be null.
     * @param val Object value
     * @return Mono of object
     */
    public Mono<Object> set(String key, String hashKey, Object val) {
        return redisOperations.opsForHash().put(key, hashKey, val).map(b -> val);
    }

}
