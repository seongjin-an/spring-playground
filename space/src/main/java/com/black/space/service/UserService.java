package com.black.space.service;

import com.black.space.domain.User;
import com.black.space.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class UserService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Transactional
    public void put(){
        User user = new User();
        user.setName("ansj7");
        user.setEmail("ansj7@abc.com");
//        userRepository.save(user);
        entityManager.persist(user);
        user.setName("newAnsj");//dirty check..
    }

    @Transactional
    public void detached(){
        User user = new User();
        user.setName("ansj13");
        user.setEmail("ansj13@abc.com");
        entityManager.persist(user);
//        entityManager.detach(user);
        user.setName("newAnsj13");
        entityManager.merge(user);
//        entityManager.flush();
//        entityManager.clear();//영속성 변경작업 제거 하지만 flush 이후부터임..
        entityManager.remove(user);

    }

    @Transactional
    public void delete(){
        User user = userRepository.findById(7L).get();
        entityManager.remove(user);
    }
}
