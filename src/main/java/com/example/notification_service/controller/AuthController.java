package com.example.notification_service.controller;

import com.example.notification_service.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class AuthController {
    @Autowired
    private JwtUtils jwtUtil;


    @PostMapping("/login")
    public String login(@RequestParam String username) {
        // Authenticate the user (you should validate credentials)
            // Generate and return JWT
        System.out.println("here" + username);
            return jwtUtil.generateToken(username);

    }
}
