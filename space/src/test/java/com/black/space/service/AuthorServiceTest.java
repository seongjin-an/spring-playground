package com.black.space.service;

import com.black.space.repository.AuthorRepository;
import com.black.space.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthorServiceTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void test_1(){
        try {
            authorService.putAuthor();
        }catch(RuntimeException error){
            System.out.println(error.getMessage());
        }
        System.out.println(">>>> " + authorRepository.findAll());
        System.out.println(">>>> " + bookRepository.findAll());
    }

    @Test
    void test_2(){
        try{
            bookService.propagation();
        }catch(RuntimeException error){
            System.out.println(error.getMessage());
        }
        System.out.println(">>>> " +bookRepository.findAll());
    }
}
