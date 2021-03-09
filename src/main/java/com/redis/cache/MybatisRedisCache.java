package com.redis.cache;

import com.component.SpringContextUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.User;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.List;

public class MybatisRedisCache implements Cache {
    public String id;
    public RedisTemplate redisTemplate;

    public MybatisRedisCache(String id) {
        this.id = id;
        redisTemplate = (RedisTemplate) SpringContextUtil.getApplicationContext().getBean("redisTemplate");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        System.out.println("PUT: " + key.toString() + "\n" + value.toString());
        ObjectMapper mapper = new ObjectMapper();
        try {
            String content = mapper.writeValueAsString(value);
            System.out.println("序列化(字符串)：" + content);
            Object obj = mapper.readValue(content, List.class).get(0);
            System.out.println("反序列化(列表)：" + obj + " 类型为：" + obj.getClass());
            System.out.println("树模型：" + mapper.readValue(content, JsonNode.class).get(0).get("name").asText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
         *        Template.Serializer.ObjectMapper         Template::opsForHash
         * Object --------------------------------> byte[] --------------------> redis
         */
        redisTemplate.opsForHash().put(getId(), key, value);
    }

    @Override
    public Object getObject(Object key) {
        /*
         *       Template::opsForHash         Template.Serializer.ObjectMapper
         * redis --------------------> byte[] --------------------------------> Object
         */
        Object value = redisTemplate.opsForHash().get(getId(), key);
        System.out.println("GET: " + key + "\n" + value);
        return value;
    }

    @Override
    public Object removeObject(Object key) {
        redisTemplate.opsForHash().delete(getId(), key);
        System.out.println("DEL: " + key);
        return null;
    }

    @Override
    public void clear() {
        System.out.println("CLEAR");
        redisTemplate.delete(getId());
    }

    @Override
    public int getSize() {
        Long size = redisTemplate.opsForHash().size(getId());
        System.out.println("SIZE: " + size);
        return size == null ? 0 : size.intValue();
    }
}
