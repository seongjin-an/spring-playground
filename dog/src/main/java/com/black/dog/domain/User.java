package com.black.dog.domain;

import com.black.dog.domain.listener.Auditable;
import com.black.dog.domain.listener.EntityListener;
import com.black.dog.domain.listener.UserEntityListener;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@EntityListeners(value = {EntityListener.class, UserEntityListener.class})
public class User implements Auditable {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist(){
        System.out.println("=====prePersist=====");
    }
    @PostPersist
    public void postPersist(){
        System.out.println("=====postPersist=====");
    }
    @PreUpdate
    public void preUpdate(){
        System.out.println("=====preUpdate=====");
    }
    @PostUpdate
    public void postUpdate(){
        System.out.println("=====postUpdate=====");
    }
    @PreRemove
    public void preRemove(){
        System.out.println("=====preRemove=====");
    }
    @PostRemove
    public void postRemove(){
        System.out.println("=====postRemove=====");
    }
    @PostLoad
    public void postLaod(){
        System.out.println("=====postLoad=====");
    }
}
