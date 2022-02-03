package com.black.dog.repository;

import com.black.dog.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void listenerTest(){
        Book book = new Book();
        book.setAuthor("ansj");
        book.setName("ansj");
        bookRepository.save(book);

        bookRepository.findAll().forEach(System.out::println);
        Book book2 = bookRepository.findById(6L).orElseThrow(RuntimeException::new);
        book2.setName("ansj2222");
        bookRepository.save(book2);
        Book book3 = bookRepository.findById(6L).orElseThrow(RuntimeException::new);
        System.out.println(book3.toString());
    }
}