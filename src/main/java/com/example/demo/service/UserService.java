package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User;
import java.util.Map;

public interface UserService extends IService<User> {
    String register(String username, String password);
    String login(String username, String password);
    Map<String, Object> loginWithInfo(String username, String password);
}
