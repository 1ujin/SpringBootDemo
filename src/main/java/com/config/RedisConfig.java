package com.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    // public JedisConnectionFactory jedisConnectionFactory;
    //
    // // 省略@Autowired的依赖注入
    // public RedisConfig(JedisConnectionFactory jedisConnectionFactory) {
    //     this.jedisConnectionFactory = jedisConnectionFactory;
    // }
    //
    // @Bean
    // @Primary
    // public RedisTemplate<String, Object> redisTemplate() {
    //     RedisTemplate<String, Object> template = new RedisTemplate<>();
    //     template.setConnectionFactory(jedisConnectionFactory);
    //     template.setKeySerializer(new StringRedisSerializer());
    //     // 可以从字符串、流或文件解析JSON https://blog.csdn.net/blwinner/article/details/99942211
    //     ObjectMapper mapper = new ObjectMapper();
    //     // jackson的自动检测机制 https://www.cnblogs.com/twoheads/p/9482448.html
    //     mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    //     mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    //     GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(mapper);
    //     template.setValueSerializer(serializer);
    //     template.setHashKeySerializer(serializer);
    //     template.setHashValueSerializer(serializer);
    //     return template;
    // }

    public RedisConfig(JedisConnectionFactory jedisConnectionFactory, RedisTemplate redisTemplate) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 可以从字符串、流或文件解析JSON https://blog.csdn.net/blwinner/article/details/99942211
        ObjectMapper mapper = new ObjectMapper();
        // jackson的自动检测机制 https://www.cnblogs.com/twoheads/p/9482448.html
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(mapper);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashKeySerializer(serializer);
        redisTemplate.setHashValueSerializer(serializer);
        System.out.println("JedisConnection工厂类已经被Redis模板类持有: " + (redisTemplate.getConnectionFactory() == jedisConnectionFactory));
        System.out.println(redisTemplate.getConnectionFactory());
        System.out.println(jedisConnectionFactory);
    }
}
