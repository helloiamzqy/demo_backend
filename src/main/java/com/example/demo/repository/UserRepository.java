package com.example.demo.repository;

import com.example.demo.entity.SysUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<SysUser, String>, UserMongoRepository {

    Optional<SysUser> findByUsername(String username);

    @Query("{'username': ?0}")
    Optional<SysUser> test(String username);
}
