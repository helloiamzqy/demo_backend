package com.example.demo.repository;

import com.example.demo.entity.SysUser;

import java.util.Optional;

public interface UserMongoRepository {

    Optional<SysUser> getUserByItsUsername(String username);
}
