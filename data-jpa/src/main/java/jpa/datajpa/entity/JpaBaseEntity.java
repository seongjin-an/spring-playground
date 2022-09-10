package jpa.datajpa.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass//필드(속성)들만 내려주는 역할, 상속은 아님
public class JpaBaseEntity {
    @Column(updatable = false)
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @PrePersist//persist 전에 이벤트 발생처리
    public void prePersist() {
        System.out.println("PRE-PERSIST POINT");
        LocalDateTime now = LocalDateTime.now();
        createdDate = now;
        updatedDate = now;
    }

    @PreUpdate//update 전
    public void preUpdate() {
        System.out.println("PRE-UPDATE POINT");
        updatedDate = LocalDateTime.now();
    }
}
