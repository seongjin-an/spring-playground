package com.black.space.domain;

import com.black.space.domain.listener.UserEntityListener;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "USERS")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners(value = UserEntityListener.class)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @OneToMany
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @ToString.Exclude
    private List<UserHistory> userHistories = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();
}

//jpa는 빈 리스트를 자동으로 넣어 주는데, 일반적으로 문제가 되지는 않지만 Persist를 하기 전에
//해당 값이 null이므로 때때로 NPT가 발생할 수도 있다. 그래서 기본 리스트를 넣어준다.

//JoinColumn을 하게되면 원치 않은 테이블이 생기는 현상이 방지됨(user_user_history), OneToMany에서 생김
//name을 지정해주면 상대 entity에 이름을 명시적으로 명명한다.