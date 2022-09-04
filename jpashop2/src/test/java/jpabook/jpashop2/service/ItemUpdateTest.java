package jpabook.jpashop2.service;

import jpabook.jpashop2.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
public class ItemUpdateTest {
    @Autowired
    EntityManager em;
    @Test
    public void updateTest() throws Exception {
        Book book = em.find(Book.class, 1L);

        //TX
        book.setName("asdf");

        //변경감지 == dirty checking
        //TX commit 시 JPA 가 더티체킹하여 DB 에 반영한다. 즉 변경 감지
    }
}
