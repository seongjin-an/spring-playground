package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),//중간 테이블에서 현 테이블쪽의 외래키 컬럼이다.
            inverseJoinColumns = @JoinColumn(name = "item_id")//중간테이블에서 상대 테이블쪽의 외래키 컬럼이다.
    )
    private List<Item> items = new ArrayList<>();
}
