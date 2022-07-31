package com.black.space.service;

import com.black.space.domain.Author;
import com.black.space.domain.Book;
import com.black.space.repository.AuthorRepository;
import com.black.space.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;

    @Transactional(rollbackFor = Exception.class)
    public void put(){
        this.putBookAndAuthor();
       //빈 클래스 내부에서 내부 호출을 할 경우 호출되는 쪽의 어노테이션이 무시되...
    }

    public void put2(){
        this.putBookAndAuthor2();
    }

    @Transactional(rollbackFor = Exception.class)//rallbackFor = Exception.class를 해줘야 checked exception역시 롤백이됨
    void putBookAndAuthor(){
        Book book = new Book();
        book.setName("START JPA");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("mars");

        authorRepository.save(author);

        throw new RuntimeException("RUNTIME EXCEPTION");//RuntimeException으로 롤백
//        throw new Exception("ERROR");
    }

    //propagation test
    @Transactional
    void putBookAndAuthor2(){
        Book book = new Book();
        book.setName("START JPA");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("mars");

        authorRepository.save(author);

        throw new RuntimeException("RUNTIME EXCEPTION");//RuntimeException으로 롤백
//        throw new Exception("ERROR");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void propagation(){
        Book book = new Book();
        book.setName("JPA JPA");

        bookRepository.save(book);
//        throw new RuntimeException("RUNTIME EXCEPTION!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    /*
    Isolation
    defualt
    READ_UNCOMMITED     //dirty read
    READ_COMMITED       //postgresql
    REPEATABLE_READ     //mysql, mariadb
    SERIALIZABLE
    아래로 갈수록.. 정합도↑, 속도↓

    1.READ_UNCOMMITED 상태에서 포커스를 테스트로 넘긴 상태에서
      DB 트랜잭션을 걸고 해당 데이터를 업데이후 커밋한 다음 디버깅하여 dirty read를 확인한다.
    2.업데이트 로직 추가 후 DB에서 해당 데이터에 대해 트랜잭션을 걸고 커밋을 하지 않고
      락이 걸리는 것을 확인한다. 이후 커밋, 롤백 두 케이스 모두 확인한다.

      반복적인 조회를 했을 때 값이 변경될 수 있는 현상을 UNREPEATABLE READ라고 한다.
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void get(Long id){
        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        entityManager.clear();

        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        entityManager.clear();

        Book book = bookRepository.findById(1L).get();
        book.setName("AAAAAAA");
        bookRepository.save(book);
    }

}
/*
checkedException이란... 간단히 말하면 컴파일 타임에 체크되는 에러라고 생각하면 된다.
 */