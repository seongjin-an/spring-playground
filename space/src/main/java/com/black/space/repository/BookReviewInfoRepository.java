package com.black.space.repository;

import com.black.space.domain.BookReviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookReviewInfoRepository extends JpaRepository<BookReviewInfo, Long> {
}
