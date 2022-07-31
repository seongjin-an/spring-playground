package com.black.space.service;

import com.black.space.domain.Book;
import com.black.space.repository.AuthorRepository;
import com.black.space.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void transactionTest(){
        try {
            bookService.putBookAndAuthor();
//            bookService.put();
        }catch(RuntimeException error){
            System.out.println(">>> " + error.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("111");
        }
        //TransactionAspectSupport
        System.out.println("BOOKS: " + bookRepository.findAll());
        System.out.println("AUTHORS: " + authorRepository.findAll());
    }

    @DisplayName("runtime exception이 발생해도 롤백이 안되는 경우")
    @Test
    void transactionTest2(){
        try {
            bookService.put2();
            //put2 메서드에 진입하는 순간 빈 내부로 진입된거고 해당 메서드가 다른 메서드를 호출하면 거기에 달린 Transactional이 무시됨
        }catch(RuntimeException e){
            System.out.println(">>> " + e.getMessage());
        }
        System.out.println("BOOKS: " + bookRepository.findAll());
        System.out.println("AUTHORS: " + authorRepository.findAll());
    }


    @Test
    void isolationTest(){
        Book book = new Book();
        book.setName("isolation");

        bookRepository.save(book);

        bookService.get(1L);

        System.out.println(">>> " + bookRepository.findAll());
    }
}