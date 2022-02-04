package com.black.space.repository;

import com.black.space.domain.Book;
import com.black.space.domain.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookReviewInfoRepositoryTest {
    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;
    @Autowired
    private BookRepository bookRepository;

//    @Test
//    public void crudTest(){
//        BookReviewInfo bookReviewInfo = new BookReviewInfo();
//        bookReviewInfo.setBookId(1L);
//        bookReviewInfo.setAverageReviewSource(4.5f);
//        bookReviewInfo.setReviewCount(2);
//        bookReviewInfoRepository.save(bookReviewInfo);
//
//        System.out.println(">>>" + bookReviewInfoRepository.findAll());
//    }

    @Test
    public void crudTest2(){
        givenBookReviewInfo();
        Book result = bookReviewInfoRepository
                .findById(1L)
                .orElseThrow(RuntimeException::new)
                .getBook();
        System.out.println(">>>"+result);

        BookReviewInfo result2 = bookRepository
                .findById(1L)
                .orElseThrow(RuntimeException::new)
                .getBookReviewInfo();
        System.out.println(">>>"+result2);
    }

    private Book givenBook(){
        Book book = new Book();
        book.setName("JPA");
        book.setAuthorId(1L);
//        book.setPublisherId(1L);
        return bookRepository.save(book);
    }

    private void givenBookReviewInfo(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewSource(4.5f);
        bookReviewInfo.setReviewCount(2);
        bookReviewInfoRepository.save(bookReviewInfo);
        System.out.println(">>>>>>>"+bookReviewInfoRepository.findAll());
    }

}