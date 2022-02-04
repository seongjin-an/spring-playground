package com.black.space.repository;

import com.black.space.domain.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
    List<UserHistory> findByUserId(Long userId);
}
