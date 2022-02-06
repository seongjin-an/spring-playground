package com.black.space.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Review extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

//    @Column(name = "user_id", insertable = false, updatable = false)
//    private Long userId;

    private String content;

    private float score;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;
}
