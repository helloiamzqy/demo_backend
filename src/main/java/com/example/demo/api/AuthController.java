package com.example.demo.api;

import com.example.demo.dto.UserAuthDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("system")
@ApiIgnore
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/connect")
    public String connect() {
        return "success";
    }

    @PostMapping("/login")
    public UserAuthDTO login(@RequestBody UserDTO dto) {
        return userService.login(dto.getUsername(), dto.getPassword());
    }

    @PostMapping("/logout")
    public String logout() {
        return userService.logout();
    }

    @PostMapping("/register")
    public String register(@RequestBody UserDTO dto) {
        return userService.register(dto);
    }
}
