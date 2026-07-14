package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody LoginDto dto) {
        Map<String, Object> result = new HashMap<>();
        try {
            String msg = userService.register(dto.getUsername(), dto.getPassword());
            result.put("code", 200);
            result.put("msg", msg);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginDto dto) {
        Map<String, Object> result = new HashMap<>();
        try {
            String token = userService.login(dto.getUsername(), dto.getPassword());
            result.put("code", 200);
            result.put("msg", "登录成功");
            result.put("token", token);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", e.getMessage());
        }
        return result;
    }
}
