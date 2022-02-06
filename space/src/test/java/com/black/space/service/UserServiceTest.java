package com.black.space.service;

import com.black.space.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void test(){
        userService.put();
        System.out.println(">>> " + userRepository.findByEmail("ansj7@abc.com"));
    }

    @Test
    void detachTest(){
        userService.detached();
        System.out.println(">>> " + userRepository.findByEmail("ansj13@abc.com"));
    }

    @Test
    void delete(){
        userService.delete();
        userRepository.findAll().forEach(System.out::println);
    }
}