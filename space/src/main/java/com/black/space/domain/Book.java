package com.black.space.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private Long authorId;

//    @Column(name = "publisher_id", insertable = false, updatable = false)
//    private Long publisherId;

    @OneToOne(mappedBy = "book")
    @ToString.Exclude
    private BookReviewInfo bookReviewInfo;
    //entity relation 을 사용하는 경우 toString이 순환참조에 걸리기 때문(stackoverflow error)
    //따라서 relation은 단방향으로 걸거나, toString exclude처리가 필요함

    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    @ToString.Exclude
    private Publisher publisher;

//    @ManyToMany
    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
//    private List<Author> authors = new ArrayList<>();
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

//    public void addAuthor(Author author){
//        this.authors.add(author);
//    }
//    public void addAuthor(Author... author){
//        Collections.addAll(this.authors, author);
//    }
    public void addBookAndAuthors(BookAndAuthor... bookAndAuthors){
        Collections.addAll(this.bookAndAuthors, bookAndAuthors);
    }
}
