package com.mapper;

import com.pojo.User;

public interface UserMapper {
    User selectUserById(long id);
}
