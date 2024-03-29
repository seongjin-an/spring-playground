package jpabook.jpashop;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class MemberV0 {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
}
