package com.black.dog.domain.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class EntityListener {
    @PrePersist
    public void prePersist(Object object){
        System.out.println("=====prePersist=====");
        if(object instanceof Auditable){
            ((Auditable) object).setCreatedAt(LocalDateTime.now());
            ((Auditable) object).setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object object){
        System.out.println("=====preUpdate=====");
        if(object instanceof Auditable){
            ((Auditable) object).setUpdatedAt(LocalDateTime.now());
        }
    }
}
