package com.black.space.repository;

import com.black.space.domain.Gender;
import com.black.space.domain.User;
import com.black.space.domain.UserHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Test
    public void userRelationTest(){
        User user = new User();
        user.setName("ANSJ");
        user.setEmail("ansj@abc.com");
        user.setGender(Gender.MALE);
        userRepository.save(user);

        user.setName("daniel");

        userRepository.save(user);

        user.setEmail("daniel@abc.com");

        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);

//        List<UserHistory> result = userHistoryRepository.findByUserId(
//                userRepository.findByEmail("daniel@abc.com").getId()
//        );
        List<UserHistory> result = userRepository.findByEmail("daniel@abc.com").getUserHistories();
        result.forEach(System.out::println);

        System.out.println("UserHistory.getUser(): " + userHistoryRepository.findAll().get(0).getUser());
    }
}