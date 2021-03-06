package com.example.demo.service.impl;

import com.example.demo.dto.UserAuthDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Permission;
import com.example.demo.entity.SysUser;
import com.example.demo.exception.ConflictException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.zklock.core.ZkLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserAuthDTO login(String username, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return buildAuthDTO(authentication);
        } catch (AuthenticationException ex) {
            throw new NotFoundException(ex.getMessage());
        }
    }

    @Override
    public String logout() {
        log.info("Logout");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        SecurityContextHolder.getContext().setAuthentication(null);
        return "success";
    }

    private UserAuthDTO buildAuthDTO(Authentication authenticate) {
        SysUser user = (SysUser) authenticate.getPrincipal();
        return new UserAuthDTO(user);
    }

    @Override
    @ZkLock(value = "user", key = "username")
    public String register(UserDTO dto) {
        Optional<SysUser> existUser = userRepository.findByUsername(dto.getUsername());
        if (existUser.isPresent()) {
            throw new ConflictException("Username is existed.");
        }
        SysUser user = new SysUser(dto.getUsername(), passwordEncoder.encode(dto.getPassword()));
        user.getPermissions().add(new Permission("USER"));
        userRepository.save(user);
        return user.getId();
    }

    @Override
    @Transactional
    public String update(UserDTO dto) {
        Optional<SysUser> userOp = userRepository.findById(dto.getId());
        if (!userOp.isPresent()) {
            throw new NotFoundException("SysUser not exist.");
        }
        SysUser user = userOp.get();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public List<UserDTO> list() {
        return userRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findUserById(String id) {
        Optional<SysUser> userOp = userRepository.findById(id);
        if (!userOp.isPresent()) {
            throw new NotFoundException("SysUser not exist.");
        }
        return toDTO(userOp.get());
    }

    private UserDTO toDTO(SysUser user) {
        return UserDTO.builder().id(user.getId()).username(user.getUsername()).build();
    }
}
