package com.invoice.entities;



import com.invoice.repositories.FileHistoryRepository;
import com.invoice.services.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

import static javax.transaction.Transactional.TxType.MANDATORY;
import static com.invoice.entities.Action.*;
@Component
public class FileEntityListener {
    @Autowired
    FileHistoryRepository fileHistoryRepository;

    @PrePersist
    public void prePersist(Invoice target) {
        perform(target, INSERTED);
    }

    @PreUpdate
    public void preUpdate(Invoice target) {
        FileHistory file=new FileHistory();
        file.setFileContent(target.toString());
        fileHistoryRepository.saveAndFlush(file);
        System.out.println("pree update");
        perform(target, UPDATED);
    }


    @PostPersist // to be called after flushing, from here we can recover the id
    @PostUpdate // after update
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void afterPresist(final Invoice saved){
        FileHistory backup = new FileHistory();
        backup.setFileContent(saved.toString());
        fileHistoryRepository.save(backup);
    }

    @PreRemove
    public void preRemove(Invoice target) {
        perform(target, DELETED);
    }


    @Transactional(propagation = Propagation.MANDATORY)
    void perform(Invoice target, Action action) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        entityManager.persist(new FileHistory(target, action));
        System.out.println("entityManager"+entityManager);
//        System.out.println("persisting employee with id = " + emp.getIdEmployee());
    }

}
