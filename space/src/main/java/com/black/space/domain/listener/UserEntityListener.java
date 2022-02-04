package com.black.space.domain.listener;

import com.black.space.domain.User;
import com.black.space.domain.UserHistory;
import com.black.space.repository.UserHistoryRepository;
import com.black.space.support.BeanUtils;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class UserEntityListener {
    @PostPersist
    @PostUpdate
    public void prePersistAndPreUpdate(Object o) {
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User) o;

        UserHistory userHistory = new UserHistory();
//        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());
        userHistory.setUser(user);

        userHistoryRepository.save(userHistory);
    }
}
