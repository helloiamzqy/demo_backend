package com.example.demo.repository;

import com.example.demo.entity.SysUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        List<SysUser> users = givenUsers();
        userRepository.saveAll(users);
    }

    @Test
    public void findByUsername() {
        Optional<SysUser> user = userRepository.findByUsername("User");
        System.out.println(user.isPresent());
    }

    private List<SysUser> givenUsers() {
        List<SysUser> users = new ArrayList<>();
        users.add(givenUser());
        return users;
    }

    private SysUser givenUser() {
        SysUser user = new SysUser();
        user.setId("1");
        user.setUsername("User");
        user.setPassword("123");
        return user;
    }
}