package com.example.demo.repository;

import com.example.demo.entity.SysUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<SysUser, String> {

    Optional<SysUser> findByUsername(String username);
}