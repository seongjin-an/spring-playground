package com.black.space.repository;

import com.black.space.domain.Book;
import com.black.space.domain.QBook;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BookRepository2 {
    private final JPAQueryFactory jpaQueryFactory;
    QBook book = QBook.book;
    public List<Book> findByName(String name){
        return jpaQueryFactory.selectFrom(book)
                .where(
                        book.name.eq("ansj"),
                        book.name.eq("ansj2")
                )
                .fetch();
    }
}
