package com.black.space.repository;

import com.black.space.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void bookTest(){
        Book book = new Book();
        book.setName("ansj");
        book.setAuthorId(1L);
//        book.setPublisherId(1L);
        book.setCategory("SCIENCE");
        bookRepository.save(book);
        System.out.println(bookRepository.findAll());
    }

    @Test
    @Transactional
    void bookRelationTest(){
        givenBookAndReview();

        User user = userRepository.findByEmail("ansj@abc.com");
        System.out.println("Review: " + user.getReviews());
        System.out.println("Book: " + user.getReviews().get(0).getBook());
        System.out.println("Publisher: " + user.getReviews().get(0).getBook().getPublisher());
    }
    private void givenBookAndReview(){
        givenReview(givenUser(), givenBook(givenPublisher()));
    }
    private User givenUser(){
//        User user = new User();
//        user.setName("ansj");
//        user.setEmail("ansj@abc.com");
//        user.setGender(Gender.MALE);
//        return userRepository.save(user);
        return userRepository.findByEmail("ansj@abc.com");
    }
    private void givenReview(User user, Book book){
        Review review = new Review();
        review.setTitle("cool jpa");
        review.setContent("it's so nice");
        review.setScore(5.0f);
        review.setUser(user);
        review.setBook(book);

        reviewRepository.save(review);
    }
    private Book givenBook(Publisher publisher){
        Book book = new Book();
        book.setName("JPA BOOK");
        book.setPublisher(publisher);
        return bookRepository.save(book);
    }
    private Publisher givenPublisher(){
        Publisher publisher = new Publisher();
        publisher.setName("ANSJ PUBLISHER");
        return publisherRepository.save(publisher);
    }
}
