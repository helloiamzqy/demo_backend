package com.example.demo.repository;

import com.example.demo.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class UserMongoRepositoryImpl implements UserMongoRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Optional<SysUser> getUserByItsUsername(String username) {
        Criteria usernameCriteria = where("username").is(username);
        SysUser one = mongoTemplate.findOne(query(usernameCriteria), SysUser.class);
        return Optional.of(one);
    }
}
