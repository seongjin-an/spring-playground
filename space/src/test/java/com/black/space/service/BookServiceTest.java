package com.black.space.service;

import com.black.space.domain.Book;
import com.black.space.repository.AuthorRepository;
import com.black.space.repository.BookRepository;
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


    @Test
    void isolationTest(){
        Book book = new Book();
        book.setName("isolation");

        bookRepository.save(book);

        bookService.get(1L);

        System.out.println(">>> " + bookRepository.findAll());
    }
}