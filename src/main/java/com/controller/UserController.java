package com.controller;

import com.domain.User;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
// 通过这里配置使下面的映射都在 /users 下
@RequestMapping(value = "/users")
public class UserController {
    // 向上转型, 创建线程安全的 Map
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    // 请求类型: Get, 功能说明: 查询用户列表
    @RequestMapping(value = "/", method = RequestMethod.GET)
    // 还可以通过 @RequestParam 从页面中传递参数来进行查询条件或者翻页信息的传递
    public List<User> getUserList() {
        List<User> r = new ArrayList<User>(users.values());
        return r;
    }

    // 请求类型: Post, 功能说明: 创建一个用户
    @RequestMapping(value = "/", method = RequestMethod.POST)
    // 除了 @ModelAttribute 绑定参数之外，还可以通过 @RequestParam 从页面中传递参数
    public String postUser(@ModelAttribute User user) {
        users.put(user.getId(), user);
        return "create success";
    }

    // 请求类型: Get, 功能说明: 根据 id 查询一个用户
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    // url 中的 id 可通过 @PathVariable 绑定到函数的参数中
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    // 请求类型: PUT, 功能说明: 根据 id 更新一个用户
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    // 处理"/users/{id}"的PUT请求，用来更新User信息
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "update success";
    }

    // 请求类型: DELETE, 功能说明: 根据 id 删除一个用户
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    // 处理"/users/{id}"的DELETE请求，用来删除User
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "delete success";
    }
}
