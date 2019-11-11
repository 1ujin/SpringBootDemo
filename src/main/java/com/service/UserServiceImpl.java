package com.service;

import com.pojo.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
    public Collection<User> getUsers() {
        return userRepository.values();
    }
}
