package com.black.space.service;

import com.black.space.domain.Author;
import com.black.space.domain.Book;
import com.black.space.repository.AuthorRepository;
import com.black.space.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void putAuthor() {

//        Book book = new Book();
//        book.setName("jpjp");
//        bookRepository.save(book);
        try {
            bookService.propagation();
        }catch(RuntimeException error){}
        Author author = new Author();
        author.setName("ans");
        authorRepository.save(author);


        throw new RuntimeException("RUNTIME EXCEPTION!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
