package com.example.demo;

import com.example.demo.entity.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class DemoApplicationContext {

    public static Optional<SysUser> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof SysUser) {
            return Optional.of((SysUser) principal);
        } else {
            return Optional.empty();
        }
    }
}
