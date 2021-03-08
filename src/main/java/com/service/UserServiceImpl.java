package com.service;

import com.mapper.UserMapper;
import com.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private static Map<Long, User> userRepository = Collections.synchronizedMap(new HashMap<>());

    static {
        User user = new User();
        user.setId(1L);
        user.setName("Jack");
        user.setAge(20);
        userRepository.put(user.getId(), user);

        User user1 = new User();
        user1.setId(2L);
        user1.setName("Tom");
        user1.setAge(24);
        userRepository.put(user1.getId(), user1);
    }

    // 基于字段的依赖注入方式
    // 正常运行，但建议用@Resource，可以byName或byType更加灵活，而@Autowired只能byType
    // @Autowired配合@Qualifier可以byName
    @Autowired
    private UserMapper userMapper;

    // 基于构造函数的依赖注入
    // 省略了@Autowired，需要将该字段定义为final
    // https://juejin.cn/post/6844904064212271117
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 基于setter的依赖注入
    // 在程序启动的时候无法拿到这个类
    @Autowired
    public void setUseMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void createUser(User user) {
        userRepository.put(user.getId(), user);
    }

    @Override
    public void updateUser(Long id, User user) {
        User u = userRepository.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        userRepository.put(user.getId(), user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.remove(id);
    }

    @Override
    public List<User> getUser(Long id) {
        System.out.println("Method getUser()");
        return userMapper.selectUserById(id);
    }

    @Override
    public Collection<User> getUsers() {
        return userRepository.values();
    }
}
