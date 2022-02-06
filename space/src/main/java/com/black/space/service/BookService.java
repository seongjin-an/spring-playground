package com.black.space.service;

import com.black.space.domain.Author;
import com.black.space.domain.Book;
import com.black.space.repository.AuthorRepository;
import com.black.space.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    @Transactional(rollbackFor = Exception.class)
    public void put(){
        this.putBookAndAuthor();
       //빈 클래스 내부에서 내부 호출을 할 경우 호출되는 쪽의 어노테이션이 무시되...
    }

    @Transactional(rollbackFor = Exception.class)
    void putBookAndAuthor(){
        Book book = new Book();
        book.setName("START JPA");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("mars");

        authorRepository.save(author);

        throw new RuntimeException("RUNTIME EXCEPTION");
//        throw new Exception("ERROR");
    }

}
/*
checkedException이란... 간단히 말하면 컴파일 타임에 체크되는 에러라고 생각하면 된다.
 */