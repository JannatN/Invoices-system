//package com.invoice.entities;
//
//
//import com.invoice.repositories.InvoiceHistoryRepository;
//import com.invoice.services.BeanUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.*;
//
//import static com.invoice.entities.Action.*;
//
////@Component
//public class InvoiceEntityListener {
//    @Autowired
//    private InvoiceHistoryRepository invoiceHistoryRepository;
//
//    @PrePersist
//    public void prePersist(Invoice target) {
//        System.out.println("pre presist InvoiceEntityListener");
//        perform(target, INSERTED);
//    }
//
//    @PreUpdate
//    public void preUpdate(Invoice target) {
//        InvoiceHistory invoiceHistory = new InvoiceHistory();
//        invoiceHistory.setInvoiceContentBefore(target);
////        file.setFileContent(target);
////        InvoiceHistory(target, action)
//        System.out.println("pefore update ");
////        System.out.println("target " + invoiceHistory.getInvoiceContentBefore());
//        System.out.println("invoiceHistory " + invoiceHistory.toString());
//
//        invoiceHistoryRepository.saveAndFlush(invoiceHistory);
//
//        perform(target, UPDATED);
//    }
//
//
//    @PostPersist // to be called after flushing, from here we can recover the id
////    @PostUpdate // after update
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void afterPresist(final Invoice saved) {
//        InvoiceHistory backup = new InvoiceHistory();
//        System.out.println("afterPresist ");
////        backup.setFileContent(saved);
////        fileHistoryRepository.save(backup);
//    }
//
//    @PreRemove
//    public void preRemove(Invoice target) {
//        perform(target, DELETED);
//    }
//
//
//    @Transactional(propagation = Propagation.MANDATORY)
//    void perform(Invoice target, Action action) {
//        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
//        entityManager.persist(new InvoiceHistory(target, action));
//        System.out.println("listener "+new InvoiceHistory(target, action));
////        System.out.println("entityManager invoice entity listener " + entityManager.toString());
////        System.out.println("persisting employee with id = " + emp.getIdEmployee());
//    }
//
//}
