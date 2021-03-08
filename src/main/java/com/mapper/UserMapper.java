package com.mapper;

import com.pojo.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.cache.decorators.LruCache;

import java.util.List;

// Mapper 接口是没有实现类的
@Mapper // 该注解效果等同于在配置类上注解 @MapperScan
// @CacheNamespace(eviction = LruCache.class)
public interface UserMapper {
    // 当调用接口方法时，接口全限名+方法名拼接字符串作为key值，可唯一定位一个MappedStatement
    // 方法不能被重载，否则光靠名字找不到
    List<User> selectUserById(Long id);
}


