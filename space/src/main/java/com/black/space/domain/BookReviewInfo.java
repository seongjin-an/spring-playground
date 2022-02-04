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
public class BookReviewInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Long bookId;
    @OneToOne(optional = false)
    private Book book;
    //optional = true 는 데이터가 있는 경우와 없는 경우까지 모두 고려되어 left outer join을 시도함
    //mappedBy를 추가하면 연관키를 현 entity에서 더 이상 소유하지 않는다. 즉, 상태 entity 의 속성에 추됨

    private float averageReviewSource;

    private int reviewCount;
}
