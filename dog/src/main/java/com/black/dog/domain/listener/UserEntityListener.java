package com.black.dog.domain.listener;

import com.black.dog.domain.User;
import com.black.dog.domain.UserHistory;
import com.black.dog.repository.UserHistoryRepository;
import com.black.dog.support.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

//@Component
public class UserEntityListener {
//    @Autowired
//    private UserHistoryRepository userHistoryRepository;
    @PrePersist
    @PreUpdate
    public void prePersistAndPreUpdate(Object o){
        System.out.println("=====prePersistAndpreUpdate");
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User)o;
        UserHistory userHistory = new UserHistory();
        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());

        userHistoryRepository.save(userHistory);
    }
}
