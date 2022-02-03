package com.black.dog.repository;

import com.black.dog.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Test
    public void crud(){
//        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
//        users.forEach(System.out::println);

        List<User> users = userRepository.findAllById(Lists.newArrayList(1L, 3L, 5L));
        users.forEach(System.out::println);
    }

    @Test
    public void listenerTest(){
        User user = new User();
        user.setEmail("111111");
        user.setName("222222222222");
        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("333333333333");
        userRepository.save(user2);
        userRepository.deleteById(4L);
    }

    @Test
    public void userHistoryTest(){
        User user = new User();
        user.setEmail("abc@abc.com");
        user.setName("ansj");

        userRepository.save(user);

        user.setName("ansj2");

        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);
        /*
        수동으로 entityListener를 만들어도 되지만 JPA에서 제공하는 것을 사용해도 됨
        1.Application 루트에 EnableJpaAuditing을 추가한다.
        2.그리고 대상 entity의 EntityListeners 어노테이션의
            value에 AuditingEntityListener.class를 추가한다.
        3.그리고 지정해야 하는 entity 프로퍼티에 CreatedDate, LastModifiedDate 등의
            어노테이션을 활용한다....
        */

    }
}