package com.example.demo.service;

import com.example.demo.dto.UserAuthDTO;
import com.example.demo.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserAuthDTO login(String username, String password);

    String logout();

    String register(UserDTO dto);

    String update(UserDTO dto);

    List<UserDTO> list();

    UserDTO findUserById(String id);
}
