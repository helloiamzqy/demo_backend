package com.example.demo.api;

import com.example.demo.entity.SysUser;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/one/{username}")
    public SysUser mongoQueryOne(@PathVariable("username") String username) {
        Optional<SysUser> user = userRepository.test(username);
        return user.orElse(new SysUser());
    }

    @GetMapping("/two/{username}")
    public SysUser mongoQueryTwo(@PathVariable("username") String username) {
        Optional<SysUser> user = userRepository.getUserByItsUsername(username);
        return user.orElse(new SysUser());
    }
}
